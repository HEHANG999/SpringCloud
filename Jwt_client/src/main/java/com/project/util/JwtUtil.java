package com.project.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;

/**
 * Jwt工具
 */
public class JwtUtil {

    /**过期时间单位毫秒*/
    private static final long EXPIRE_TIME = 1000*50;//50秒后过期

    /**token私钥*/
    private static final String TOKEN_SECRET = "hehang666";


    /**生成签名*/
    public static String sign(String userName,String userPass){
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);//当前时间加过期时间

        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create().withHeader(header)
                .withClaim("loginName",userName)//用户名，可多个添加
                .withClaim("userPass",userPass)
                .withExpiresAt(date).sign(algorithm);
    }


    /**用于验证*/
    public static boolean verity(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }

    }
}