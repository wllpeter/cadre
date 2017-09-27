package com.ddb.xaplan.cadre.common.tool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;

import com.ddb.xaplan.cadre.common.BasePre;
import com.ddb.xaplan.cadre.common.HeaderName;
import com.ddb.xaplan.cadre.exception.ErrorTokenException;

public class UserInfoTool {
	public static String getUserid(HttpServletRequest request, RedisTemplate<String, Serializable> redisTemplate) {
		String token = request.getHeader(HeaderName.HEADER_NAME);
		String userid = null;
		if (token != null) {
			userid = (String) redisTemplate.opsForHash().get(token, BasePre.USERID);
			if (userid == null) {
				throw new ErrorTokenException();
			}
		} else {
			throw new ErrorTokenException();
		}
		return userid;
	}

	@SuppressWarnings("unchecked")
	public static List<String> getUserRoles(HttpServletRequest request,
			RedisTemplate<String, Serializable> redisTemplate) {
		String token = request.getHeader(HeaderName.HEADER_NAME);
		List<String> roles = null;
		if (token != null) {
			roles = (List<String>) redisTemplate.opsForHash().get(token, BasePre.ROLES);
		} else {
			throw new ErrorTokenException();
		}
		if (roles == null) {
			roles = new ArrayList<>();
		}
		return roles;
	}

	public static String getUseridWithoutEx(HttpServletRequest request,
			RedisTemplate<String, Serializable> redisTemplate) {
		String token = request.getHeader(HeaderName.HEADER_NAME);
		String userid = null;
		if (token != null) {
			userid = (String) redisTemplate.opsForHash().get(token, BasePre.USERID);
		}
		return userid;
	}

	@SuppressWarnings("unchecked")
	public static List<String> getUserRolesWithoutEx(HttpServletRequest request,
			RedisTemplate<String, Serializable> redisTemplate) {
		String token = request.getHeader(HeaderName.HEADER_NAME);
		List<String> roles = null;
		if (token != null) {
			roles = (List<String>) redisTemplate.opsForHash().get(token, BasePre.ROLES);
		}
		if (roles == null) {
			roles = new ArrayList<>();
		}
		return roles;
	}
}
