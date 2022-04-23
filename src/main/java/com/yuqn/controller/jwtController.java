package com.yuqn.controller;

import com.yuqn.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class jwtController {
//    创建token
    @GetMapping(value = "/setJwt")
    public String setJwt(HttpServletResponse response){
        String jwtUtil=JwtUtil.creatJwt();
//        把token放在请求头
        response.setHeader("token",jwtUtil);
        System.out.println("创建一个token内容如下："+jwtUtil);
        return jwtUtil;
    }

//    获取数据
    @GetMapping(value = "/getJwt")
    public String getJwt(HttpServletRequest request){
//        获取请求头某个内容
        String token=request.getHeader("token");

        System.out.println("获取的token为"+token);
        String jwtUtil=JwtUtil.getTokenText(token);
        return jwtUtil;
    }

//    校检token
    @GetMapping(value = "/ifJwt")
    public boolean ifJwt(){
        boolean ifjwt=JwtUtil.ifTrue("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZXh0Ijoi5a2Y5pS-5pWw5o2uIiwiZXhwIjoxNjUwNjIxMjAwLCJpYXQiOjE2NTA2MjA5MDB9.LeKu8jfCH9bzOLzK4lHYkyWZhOQv4BS_kjwrO4O2wpE","11111");
        return ifjwt;
    }
}
