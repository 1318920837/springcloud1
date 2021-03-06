package com.qf.v16userservice.until;/*
@author:g
@Date:2019/8/16
    */

import io.jsonwebtoken.*;

import java.util.Date;

public class JWTUtil {
    /**
     * @author huangguizhao
     */


        //密钥由调用方来决定
        private String secretKey;

        //有效期也由调用方来决定
        private long ttl;

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public long getTtl() {
            return ttl;
        }

        public void setTtl(long ttl) {
            this.ttl = ttl;
        }

        public String createJwtToken(String id,String subject){
            long now = System.currentTimeMillis();
            JwtBuilder jwtBuilder = Jwts.builder()
                    .setId(id).setSubject(subject)
                    .setIssuedAt(new Date(now))
                    .signWith(SignatureAlgorithm.HS256,secretKey);
            if(ttl > 0){
                jwtBuilder.setExpiration(new Date(now+ttl));
            }
            return jwtBuilder.compact();
        }

        public Claims parseJwtToken(String jwtToken)throws SignatureException{
            return Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwtToken).getBody();
        }

}
