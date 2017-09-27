package com.ddb.xaplan.cadre.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", HttpStatus.UNAUTHORIZED.value());
		jsonObject.put("message", "请登录");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "application/json; charset=utf-8");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getOutputStream().write(jsonObject.toString().getBytes());
	}
}
