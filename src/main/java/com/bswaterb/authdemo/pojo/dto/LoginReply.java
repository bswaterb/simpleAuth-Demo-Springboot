package com.bswaterb.authdemo.pojo.dto;

import lombok.Data;

/**
 * @author bswaterb
 * @date 2022-11-04 20:36:56
 */
@Data
public class LoginReply {
    String userId;
    String userName;
    String auth;
    String token;
}
