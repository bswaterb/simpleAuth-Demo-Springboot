package org.example.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.common.error.BizException;
import org.example.common.error.ErrEnum;

import java.util.Calendar;
import java.util.Map;

/**
 * @author bswaterb
 * @date 2022-11-05 11:11:31
 */
public class JwtUtils {
    // 定义统一密钥
    private static String secretKey = "example_secret_key";

    // 生成 token
    public static String generateToken(Map<String,String> map){
        //创建JWTBuilder
        JWTCreator.Builder builder = JWT.create();
        //遍历传入的map内的信息，为token添加payload/负载
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        //设置 token 有效时间为 30分钟
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,30);
        //返回生成的token
        return builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(secretKey));
    }

    // 验证 token 是否合法
    public static void verify(String token){
        try{
            JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
        }catch (IllegalArgumentException e){
            throw new BizException(ErrEnum.TOKEN_INVALID);
        }catch (TokenExpiredException e){
            throw new BizException(ErrEnum.TOKEN_EXPIRED);
        }catch (Exception e){
            throw new BizException(ErrEnum.TOKEN_UNKNOWN_ERROR);
        }
    }


    // 解码 token
    private static DecodedJWT getTokenInfo(String token){
        DecodedJWT decoder = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
        return decoder;
    }


    //检验不带 Bearer 头的 token，需要 token 未过期
    public static String getPayloadClaim(String token,String claim){
        return getTokenInfo(token).getClaim(claim).asString();
    }

    //校验不带 Bearer 头的 token ，不校验token是否过期
    public static String  getPayloadClaimWithoutVerify(String token,String claim){
        return JWT.decode(token).getClaim(claim).asString();
    }
}
