package org.example.user.pojo.dto;

import lombok.Data;

/**
 * @author bswaterb
 * @date 2022-11-05 11:44:06
 */
@Data
public class LoginReply {
    String id;
    String name;
    String auth;
    String token;
}
