package com.epam.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
//	public void validateToken(String token) {
//        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
//    }

//	public String extractUsername(String token) {
//		return extractClaim(token, Claims::getSubject);
//	}
//
//	public Date extractExpiration(String token) {
//		return extractClaim(token, Claims::getExpiration);
//	}
//
//	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = extractAllClaims(token);
//		return claimsResolver.apply(claims);
//	}
//
//	private Claims extractAllClaims(String token) {
//		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
//	}
//
//	private Boolean isTokenExpired(String token) {
//		return extractExpiration(token).before(new Date());
//	}
//
//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String username = extractUsername(token);
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	}

//	public String generateToken(String username) {
//		Map<String, Object> claims = new HashMap<>();
//		return createToken(claims, username);
//	}
//
//	public String createToken(Map<String, Object> claims, String username) {
//		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*30*30))
//				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//	}
//
//	private Key getSignKey() {
//		byte[] key = Decoders.BASE64.decode("nfo1ZZOwUg66uFdCNFUnHoD0KYBOG9KHNh8fQsxYvpwyUmHYhVocPbyNgi8XYqIt");
//		return Keys.hmacShaKeyFor(key);
//	}
	 public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


	    public void validateToken(final String token) {
	        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	    }


	    public String generateToken(String userName) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, userName);
	    }

	    private String createToken(Map<String, Object> claims, String userName) {
	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(userName)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	    }

	    private Key getSignKey() {
	        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }

}
