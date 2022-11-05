package org.example.user.pojo.dto;

import lombok.Data;

/**
 * @author bswaterb
 * @date 2022-11-05 11:43:26
 */
@Data
public class LoginRequest {
    String id;
    String password;

    @Override
    public String toString() {
        return "LoginRequest{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
