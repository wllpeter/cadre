package com.ddb.xaplan.cadre.exception;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.alibaba.fastjson.JSONObject;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestPartException.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public JSONObject accessMissingServletRequestPartExceptionHandler(MissingServletRequestPartException e,
			HttpServletResponse response) {
		JSONObject resp = new JSONObject();
		resp.put("status", HttpStatus.BAD_REQUEST.value());
		resp.put("message", e.getMessage());
		logger.info(e.getMessage(), e);
		return resp;
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public JSONObject accessDeniedExceptionHandler(AccessDeniedException e, HttpServletResponse response) {
		JSONObject resp = new JSONObject();
		resp.put("status", HttpStatus.FORBIDDEN.value());
		resp.put("message", "权限不足");
		return resp;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public JSONObject httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e,
			HttpServletResponse response) {
		JSONObject resp = new JSONObject();
		resp.put("status", HttpStatus.BAD_REQUEST.value());
		resp.put("message", e.getMessage());
		logger.error(e.getMessage(), e);
		return resp;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public JSONObject methodArgumentTypeMismatchExceptionHandler(HttpRequestMethodNotSupportedException e,
			HttpServletResponse response) {
		JSONObject resp = new JSONObject();
		resp.put("status", HttpStatus.BAD_REQUEST.value());
		resp.put("message", e.getMessage());
		logger.error(e.getMessage(), e);
		return resp;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public JSONObject methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e,
			HttpServletResponse response) {
		JSONObject resp = new JSONObject();
		resp.put("status", HttpStatus.BAD_REQUEST.value());
		resp.put("message", "参数类型不对");
		logger.error(e.getMessage(), e);
		return resp;
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(ParameterErrorException.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public JSONObject parameterErrorExceptionHandler(ParameterErrorException e, HttpServletResponse response) {
		JSONObject resp = new JSONObject();
		resp.put("status", HttpStatus.BAD_REQUEST.value());
		resp.put("message", e.getMessage());
		return resp;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public JSONObject missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e,
			HttpServletResponse response) {
		JSONObject resp = new JSONObject();
		resp.put("status", HttpStatus.BAD_REQUEST.value());
		resp.put("message", e.getMessage());
		logger.error(e.getMessage(), e);
		return resp;
	}

	@ExceptionHandler(ErrorTokenException.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public JSONObject errorTokenExceptionHandler(ErrorTokenException e, HttpServletResponse response) {
		JSONObject resp = new JSONObject();
		resp.put("status", HttpStatus.UNAUTHORIZED.value());
		resp.put("message", "token已经过期");
		logger.error(e.getMessage(), e);
		return resp;
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public MyExceptionResponse exceptionHandler(RuntimeException e, HttpServletResponse response) {
		MyExceptionResponse resp = new MyExceptionResponse();
		resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		resp.setMessage("网络异常");
		logger.error(e.getMessage(), e);
		return resp;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public MyExceptionResponse exceptionHandler(Exception e, HttpServletResponse response) {
		MyExceptionResponse resp = new MyExceptionResponse();
		resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		resp.setMessage("网络异常");
		logger.error(e.getMessage(), e);
		return resp;
	}

}
