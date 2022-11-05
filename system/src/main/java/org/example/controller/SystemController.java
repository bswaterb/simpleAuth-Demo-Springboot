package org.example.controller;

import org.example.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bswaterb
 * @date 2022-11-05 11:33:33
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    @GetMapping("/test")
    public Result testConnection(){
        return Result.success("连接成功");
    }
}
