package com.qf.jwt;/*
@author:g
@Date:2019/8/16
    */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class JwtTest {
    @Test
    public void jwtTest(){
        JwtBuilder builder = Jwts.builder()
                .setId("666").setSubject("明明很爱你")
                .setIssuedAt(new Date())
                //添加自定义属性
                .claim("role","admin")
                .setExpiration(new Date(new Date().getTime()+20000))
                .signWith(SignatureAlgorithm.HS256,"123java");

        String jwtToken = builder.compact();
        System.out.println(jwtToken);
    }
    @Test
    public void jwtTokenParseTest(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLmmI7mmI7lvojniLHkvaAiLCJpYXQiOjE1NjU5NDU5ODcsInJvbGUiOiJhZG1pbiIsImV4cCI6MTU2NTk0NjU4N30.xrv888KDjw3PSWG406q0LH-ZSKGe6yBzBXulOvNYUKI";

        Claims claims = Jwts.parser().setSigningKey("123java")
                .parseClaimsJws(token).getBody();

        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getExpiration());
        //获取属性
        System.out.println(claims.get("role"));

    }
}
