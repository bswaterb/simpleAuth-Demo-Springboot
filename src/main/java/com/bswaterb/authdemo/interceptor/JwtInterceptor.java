package com.bswaterb.authdemo.interceptor;

import com.bswaterb.authdemo.aop.exception.BizException;
import com.bswaterb.authdemo.aop.exception.ErrEnum;
import com.bswaterb.authdemo.pojo.etc.TokenPayload;
import com.bswaterb.authdemo.util.JwtUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bswaterb
 * @date 2022-11-04 16:46:14
 */

// 对用户请求进行认证
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        // 响应 OPTIONS 请求，解决跨域问题
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        final String token = getToken(request);
        // 检查 token 是否有效
        JwtUtils.verify(token);
        // 构造 payload 并添加进请求的属性中
        TokenPayload payload = new TokenPayload(token);
        request.setAttribute("payload",payload);

        //放行
        return true;
    }



    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader==null || !authHeader.startsWith("Bearer")) {
            throw new BizException(ErrEnum.TOKEN_INVALID);
        }
        return authHeader.substring(7);
    }
}
