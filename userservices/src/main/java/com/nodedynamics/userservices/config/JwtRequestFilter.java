package com.nodedynamics.userservices.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.JWT;
import com.google.gson.Gson;
import com.nodedynamics.userservices.common.JwtTokenUtil;
import com.nodedynamics.userservices.common.SecurityConstants;
import com.nodedynamics.userservices.service.impl.AuthServiceImpl;

import lombok.extern.slf4j.Slf4j;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter{
	

	@Autowired
	private AuthServiceImpl userDetailsService;
	
	@Autowired
	Gson gson;
	

	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//UserAuthModel UM =gson.fromJson(request.getReader(), UserAuthModel.class);

		
		
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = null;
		
		//check if Request header contains Bearer token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

		}else {
			
			   String token = JWT.create()
		                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
		                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));
			   response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		}
		
		

		filterChain.doFilter(request, response);
	}

}
