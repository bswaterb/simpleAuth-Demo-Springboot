package com.bswaterb.authdemo.controller;

import com.bswaterb.authdemo.pojo.dto.LoginReply;
import com.bswaterb.authdemo.pojo.dto.LoginRequest;
import com.bswaterb.authdemo.result.Result;
import com.bswaterb.authdemo.util.JwtUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bswaterb
 * @date 2022-11-04 20:29:46
 */

@RestController
public class LoginController {

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        if (request.getUserId().equals("222050354") && request.getPassword().equals("123456")){
            Map<String, String> payloadMap = new HashMap();
            payloadMap.put("id", "222050354");
            payloadMap.put("name", "bswaterb");
            payloadMap.put("auth", "admin");
            String token = "Bearer "+ JwtUtils.generateToken(payloadMap);
            //返回登录成功的标识
            LoginReply loginReply = new LoginReply();
            loginReply.setAuth(payloadMap.get("auth"));
            loginReply.setUserId(payloadMap.get("id"));
            loginReply.setUserName(payloadMap.get("name"));
            loginReply.setToken(token);
            return Result.success(loginReply);
        }
        return Result.error(-1, "To Be Done...");
    }
}
