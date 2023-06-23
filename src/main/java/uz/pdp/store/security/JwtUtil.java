package uz.pdp.store.security;//package org.shop.system.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import uz.pdp.store.helper.DateUtil;

import static uz.pdp.store.helper.DateUtil.getCurrentDate;


@PropertySource(value = {"classpath:application.properties"})
@Component
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String key;


    public String generateToken(String id){
        return Jwts.builder()
                .claim("sub", id)
                .claim("iat", getCurrentDate())
                .claim("exp", DateUtil.getDateAfterFiveHour())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }


    public Object getClaim(String token, String claim){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .get(claim);
    }

    public Boolean checkExpiration(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration().after(getCurrentDate());
    }


}
