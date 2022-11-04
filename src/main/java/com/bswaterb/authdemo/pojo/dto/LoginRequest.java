package com.bswaterb.authdemo.pojo.dto;

import lombok.Data;

/**
 * @author bswaterb
 * @date 2022-11-04 20:32:19
 */
@Data
public class LoginRequest {
    String userId;
    String password;
}
