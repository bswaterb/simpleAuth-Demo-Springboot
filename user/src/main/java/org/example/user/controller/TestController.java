package org.example.user.controller;

import org.example.common.annotation.CheckAuth;
import org.example.common.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bswaterb
 * @date 2022-11-05 11:45:55
 */
@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @PostMapping("/auth")
    @CheckAuth(auth = "teacher")
    public Result AuthTest(){
        return Result.success("权限检验通过");
    }
}
