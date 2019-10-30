package com.nodedynamics.userservices.common;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	

	@Value("${jwt.secret}")
	private String secret;
	

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
	return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
    //for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	//check if the token has expired
	private Boolean isTokenExpired(String token) {
	final Date expiration = getExpirationDateFromToken(token);
	return expiration.before(new Date());
	}
	

	//generate token for user
	public String generateToken() {
	Map<String, Object> claims = new HashMap<>();
	return doGenerateToken(claims);
	}
	
	private String doGenerateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
		.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	//validate token
	public Boolean validateToken(String token) {
	return isTokenExpired(token);
	}
	

}
