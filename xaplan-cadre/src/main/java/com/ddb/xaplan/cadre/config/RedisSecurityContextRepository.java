package com.ddb.xaplan.cadre.config;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SaveContextOnUpdateOrErrorResponseWrapper;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.web.util.WebUtils;

import com.ddb.xaplan.cadre.common.BasePre;
import com.ddb.xaplan.cadre.common.HeaderName;

public class RedisSecurityContextRepository implements SecurityContextRepository {

	@Autowired
	private final RedisTemplate<String, Serializable> redisTemplate;

	public static final String SPRING_SECURITY_CONTEXT_KEY = "SPRING_SECURITY_CONTEXT";

	protected final Log logger = LogFactory.getLog(this.getClass());

	private boolean disableUrlRewriting = false;
	private boolean isServlet3 = ClassUtils.hasMethod(ServletRequest.class, "startAsync");

	private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	public RedisSecurityContextRepository(final RedisTemplate<String, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
		HttpServletRequest request = requestResponseHolder.getRequest();
		HttpServletResponse response = requestResponseHolder.getResponse();
		String token = request.getHeader(HeaderName.HEADER_NAME);
		SecurityContext context = readSecurityContextFromHeader(request, HeaderName.HEADER_NAME);

		if (context == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("No SecurityContext was available from the headerName: " + token + ". "
						+ "A new one will be created.");
			}
			context = generateNewContext();

		}

		SaveToRedisResponseWrapper wrappedResponse = new SaveToRedisResponseWrapper(response, request, context);
		requestResponseHolder.setResponse(wrappedResponse);

		if (isServlet3) {
			requestResponseHolder.setRequest(new Servlet3SaveToRedisRequestWrapper(request, wrappedResponse));
		}

		return context;
	}

	protected SecurityContext generateNewContext() {
		return SecurityContextHolder.createEmptyContext();
	}

	private SecurityContext readSecurityContextFromHeader(HttpServletRequest request, String headerName) {
		final boolean debug = logger.isDebugEnabled();

		if (headerName == null) {
			if (debug) {
				logger.debug("No HeaderName currently exists");
			}
			return null;
		}

		// headerToken exists, so try to obtain a context from it.

		String headerToken = request.getHeader(headerName);
		Object obj = null;
		if (headerToken == null) {
			if (debug) {
				logger.debug("headerToken returned null object for SPRING_SECURITY_CONTEXT");
			}

			return null;
		} else {
			// obj = redisTemplate.opsForValue().get(headerToken);
			obj = redisTemplate.opsForHash().get(headerToken, BasePre.SECURITY_CONTEXT);
			if (obj == null) {
				if (debug) {
					logger.debug("headerToken returned null object for SPRING_SECURITY_CONTEXT");
				}
			} else {
				redisTemplate.expire(headerToken, HeaderName.TOKEN_EXPIRE, TimeUnit.MINUTES);
			}
		}

		if (!(obj instanceof SecurityContext)) {
			if (logger.isWarnEnabled()) {
				logger.warn(headerName + " did not contain a SecurityContext but contained: '" + obj
						+ "'; are you improperly modifying the HttpSession directly "
						+ "(you should always use SecurityContextHolder) or using the HttpSession attribute "
						+ "reserved for this class?");
			}

			return null;
		}

		if (debug) {
			logger.debug("Obtained a valid SecurityContext from " + headerName + ": '" + obj + "'");
		}

		return (SecurityContext) obj;
	}

	@Override
	public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

		SaveToRedisResponseWrapper responseWrapper = WebUtils.getNativeResponse(response,
				SaveToRedisResponseWrapper.class);
		if (responseWrapper == null) {
			throw new IllegalStateException("Cannot invoke saveContext on response " + response
					+ ". You must use the HttpRequestResponseHolder.response after invoking loadContext");
		}
		// saveContext() might already be called by the response wrapper
		// if something in the chain called sendError() or sendRedirect(). This
		// ensures we
		// only call it
		// once per request.
		if (!responseWrapper.isContextSaved()) {
			responseWrapper.saveContext(context);
		}

	}

	@Override
	public boolean containsContext(HttpServletRequest request) {
		String headerToken = request.getHeader(HeaderName.HEADER_NAME);
		if (headerToken == null) {
			return false;
		}
		return redisTemplate.opsForHash().get(headerToken, BasePre.SECURITY_CONTEXT) != null;
	}

	private static class Servlet3SaveToRedisRequestWrapper extends HttpServletRequestWrapper {
		private final SaveContextOnUpdateOrErrorResponseWrapper response;

		public Servlet3SaveToRedisRequestWrapper(HttpServletRequest request,
				SaveContextOnUpdateOrErrorResponseWrapper response) {
			super(request);
			this.response = response;
		}

		@Override
		public AsyncContext startAsync() {
			response.disableSaveOnResponseCommitted();
			return super.startAsync();
		}

		@Override
		public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
				throws IllegalStateException {
			response.disableSaveOnResponseCommitted();
			return super.startAsync(servletRequest, servletResponse);
		}
	}

	final class SaveToRedisResponseWrapper extends SaveContextOnUpdateOrErrorResponseWrapper {

		private final HttpServletRequest request;
		private final SecurityContext contextBeforeExecution;
		private final Authentication authBeforeExecution;

		public SaveToRedisResponseWrapper(HttpServletResponse response, HttpServletRequest request,
				SecurityContext context) {
			super(response, disableUrlRewriting);
			this.request = request;
			this.contextBeforeExecution = context;
			this.authBeforeExecution = context != null ? context.getAuthentication() : null;
		}

		@Override
		protected void saveContext(SecurityContext context) {
			final Authentication authentication = context.getAuthentication();
			String token = request.getHeader(HeaderName.HEADER_NAME);

			// See SEC-776
			if (authentication == null || trustResolver.isAnonymous(authentication)) {
				if (logger.isDebugEnabled()) {
					logger.debug(
							"SecurityContext is empty or contents are anonymous - context will not be stored in HttpSession.");
				}

				if (authBeforeExecution != null) {

				}
				return;
			}

			if (token != null) {
				if (contextChanged(context)
						|| redisTemplate.opsForHash().get(token, BasePre.SECURITY_CONTEXT) == null) {
					// redisTemplate.opsForValue().set(token, context, 30,
					// TimeUnit.MINUTES);
					redisTemplate.opsForHash().put(token, BasePre.SECURITY_CONTEXT, context);
					if (logger.isDebugEnabled()) {
						logger.debug("SecurityContext '" + context + "' stored to Redis: '" + token);
					}
				}
				redisTemplate.expire(token, HeaderName.TOKEN_EXPIRE, TimeUnit.MINUTES);
			}

		}

		private boolean contextChanged(SecurityContext context) {
			return context != contextBeforeExecution || context.getAuthentication() != authBeforeExecution;
		}
	}

	public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
		Assert.notNull(trustResolver, "trustResolver cannot be null");
		this.trustResolver = trustResolver;
	}
}
