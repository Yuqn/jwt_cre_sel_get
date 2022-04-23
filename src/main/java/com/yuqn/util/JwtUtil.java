package com.yuqn.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

//    生成token
    public static String creatJwt(){
//        设置头部信息
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("alg","HS256");
        map.put("typ","JWT");
//        设置载体信息
        String val="存放数据";
//        过时时间
        Calendar nowTime=Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,5);
        Date expiresTime = nowTime.getTime();
//        定义常量，用于特殊算法计算拼接尾部，用于公共秘钥加密
        String secret="11111";
        String jwt=JWT.create()
                    .withHeader(map) //第一部分内容
                    .withClaim("text",val) //存放信息
                    .withIssuedAt(new Date()) //签发时间
                    .withExpiresAt(expiresTime) //结束时间
                    .sign(Algorithm.HMAC256(secret));  //用公共秘钥加密
        return jwt;
    }

//    获取token内容数据
    public static String getTokenText(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("text").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

//    token校检器
    public static boolean ifTrue(String token,String secret){
        try{
//            创建校检器
            Algorithm algorithm=Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier =JWT.require(algorithm).build();

//            进行校检
            DecodedJWT jwt=jwtVerifier.verify(token);
            return true;

        }catch (Exception exception){
            return false;
        }
    }

}
