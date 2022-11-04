package com.bswaterb.authdemo.aop.auth;

import com.bswaterb.authdemo.annotation.CheckAuth;
import com.bswaterb.authdemo.aop.exception.BizException;
import com.bswaterb.authdemo.aop.exception.ErrEnum;
import com.bswaterb.authdemo.pojo.etc.TokenPayload;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bswaterb
 * @date 2022-11-04 16:07:49
 */

@Aspect
@Component
// 在打了 @CheckAuth 注解的接口方法被执行前会先执行此处的 checkAuth() 方法
public class AuthHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthHandler.class);
    @Pointcut(value = "execution(public * com.bswaterb.authdemo.controller..*(..))")
    public void start() {

    }

    @Before("start()")
    public void checkAuth(JoinPoint joinPoint) {
        // 获取本次请求调用的方法对应的信息
        MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
        Method method = joinPointObject.getMethod();

        // 判断该方法是否被标注了 @CheckAuth 注解
        if (hasAnnotationOnMethod(method, CheckAuth.class)) {
            TokenPayload payload = getPayload();
            String id = payload.getId();
            String name = payload.getName();
            String auth = payload.getAuth();
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date());
            String url = getRequestUrl();
            int apiAuth = Integer.parseInt(getMethodAuthLevel(method));
            int userAuth = Integer.parseInt(payload.getAuth());
            logger.info("用户 {} ({}) 于 {} 访问接口 {}, token为 {},用户访问权限为 {}(Level: {})",name,id,date,url,getHeaderToken().substring(7),auth, userAuth);
            if (userAuth < apiAuth) {
                throw new BizException(ErrEnum.USER_AUTH_NOT_PASS);
            }
        }
    }

    // 判断 method 是否携带 annotationClz 对应的注解
    private boolean hasAnnotationOnMethod(Method method, Class annotationClz) {
        Annotation annotation = method.getAnnotation(annotationClz);
        if (annotation == null) {
            return false;
        } else {
            return true;
        }
    }

    // 获取方法标注的权限
    private String getMethodAuthLevel(Method method) {
        String auth = "-1";
        if (hasAnnotationOnMethod(method, CheckAuth.class)) {
            CheckAuth checkAuth = method.getAnnotation(CheckAuth.class);
            auth = checkAuth.auth();
            if (auth.equals("user")) {
                return "1";
            } else if (auth.equals("teacher")) {
                return "2";
            } else if (auth.equals("admin")) {
                return "3";
            }
        } else {
            throw new IllegalArgumentException();
        }
        return auth;
    }

    /*
        以下为部分工具方法
        getServletRequest() -> 获取本次请求的详细信息
        getHeaderToken() -> 获取携带在请求头 Authorization 字段中的值
        getPayload() -> 获取携带在请求中属性 payload 对应的值
        getRequestUrl() -> 获取本次请求的 url
     */
    private String getHeaderToken() {
        HttpServletRequest request = getServletRequest();
        String token = request.getHeader("Authorization");
        return token.substring(7);
    }

    private TokenPayload getPayload() {
        HttpServletRequest request = getServletRequest();
        return (TokenPayload) request.getAttribute("payload");
    }

    private String getRequestUrl() {
        HttpServletRequest request = getServletRequest();
        return request.getRequestURL().toString();
    }

    private HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }



}
