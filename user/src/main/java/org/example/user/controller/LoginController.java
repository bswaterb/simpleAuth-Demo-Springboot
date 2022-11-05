package org.example.user.controller;

import org.example.common.utils.JwtUtils;
import org.example.user.pojo.dto.LoginReply;
import org.example.user.pojo.dto.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.common.result.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bswaterb
 * @date 2022-11-05 10:31:27
 */
@RestController
@RequestMapping("/api/v1")
public class LoginController {
    @PostMapping("/login/student")
    public Result StuLogin(@RequestBody LoginRequest request){
        System.out.println(request);
        if (request.getId().equals("222050354") && request.getPassword().equals("123456")){
            Map<String, String> payloadMap = new HashMap();
            payloadMap.put("id", "222050354");
            payloadMap.put("name", "bswaterb");
            payloadMap.put("auth", "admin");
            String token = "Bearer "+ JwtUtils.generateToken(payloadMap);
            //返回登录成功的标识
            LoginReply loginReply = new LoginReply();
            loginReply.setAuth(payloadMap.get("auth"));
            loginReply.setId(payloadMap.get("id"));
            loginReply.setName(payloadMap.get("name"));
            loginReply.setToken(token);
            return Result.success(loginReply);
        }
        return Result.error(-1, "To Be Done...");
    }
}
