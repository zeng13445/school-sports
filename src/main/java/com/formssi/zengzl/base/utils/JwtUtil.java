package com.formssi.zengzl.base.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    private static String key;

    private static long ttl;

    /**
     * create JWT
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJWT(Long id, String subject, Integer roles){
        long nowMillis = System.currentTimeMillis();
        // Private key and encryption algorithm
        Algorithm algorithm = Algorithm.HMAC256(key);
        // Set header info
        HashMap<String, Object> header = new HashMap<>(3);
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        String token = JWT.create()
                .withHeader(header)
                .withClaim("id", id)
                .withClaim("subject", subject)
                .withClaim("roles", roles)
                .withExpiresAt(new Date(nowMillis + ttl))
                .sign(algorithm);

        return token;
    }

    /**
     * parse JWT
     * @param jwtStr
     * @return
     */
    public DecodedJWT parseJWT(String jwtStr) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(jwtStr);
        return jwt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
