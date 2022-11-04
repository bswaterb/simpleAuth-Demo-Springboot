package com.bswaterb.authdemo.pojo.etc;

import com.bswaterb.authdemo.util.JwtUtils;
import lombok.Data;

/**
 * @author bswaterb
 * @date 2022-11-04 16:32:44
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
