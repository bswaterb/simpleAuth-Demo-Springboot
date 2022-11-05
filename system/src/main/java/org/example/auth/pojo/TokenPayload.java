package org.example.auth.pojo;

import lombok.Data;
import org.example.common.utils.JwtUtils;

/**
 * @author bswaterb
 * @date 2022-11-05 11:11:06
 */
@Data
public class TokenPayload {
    private String id;
    private String name;
    private String auth;

    public TokenPayload() {

    }

    public TokenPayload(String token) {
        this.id = JwtUtils.getPayloadClaim(token,"id");
        this.name = JwtUtils.getPayloadClaim(token, "name");
        this.auth = JwtUtils.getPayloadClaim(token, "auth");
    }
}
