package com.example.springboot_son.utils.redis;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    // JWT自定义密钥
    private static final String SECRET_KEY = "ZQ1GDelMxql5MRhm76WQkJn21iHxmI3T";
    // 过期时间24小时
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    // 缓存前缀
    private static final String CACHE_PREFIX = "login_user_";

    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得redis缓存的key
     * @return key
     */
    public static String getCacheKey(String username) {
        return CACHE_PREFIX + username;
    }

    /**
     * 生成签名
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            // 附带username信息
            return JWT.create()
                    .withJWTId(UUID.randomUUID().toString())
                    .withClaim("username", username)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
}

