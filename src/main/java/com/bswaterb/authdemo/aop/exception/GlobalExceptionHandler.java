package com.bswaterb.authdemo.aop.exception;

import com.bswaterb.authdemo.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bswaterb
 * @date 2022-11-04 18:41:23
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(value = BizException.class)
    public Result handler(HttpServletRequest request, BizException e) {
        logger.error("请求 {} 时发生业务异常：{}", request.getRequestURL(), e);
        return Result.error(e.getErrCode(), e.getErrMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    public Result handler(HttpServletRequest request, NullPointerException e) {
        logger.error("请求 {} 时发生空指针异常：{}", request.getRequestURL(), e);
        return Result.error(ErrEnum.NULL_POINTER);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result handler(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        logger.error("请求 {} 时使用了不支持的请求方式({})", request.getRequestURL(), request.getMethod());
        return Result.error(ErrEnum.REQUEST_METHOD_ERROR);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(HttpServletRequest request, IllegalArgumentException e) {
        logger.error("请求 {} 时携带的参数出现错误：{}",request.getRequestURL(), e);
        return Result.error(ErrEnum.CONTENT_NOT_MATCH);
    }

    @ExceptionHandler(value = Exception.class)
    public Result handler(HttpServletRequest request, Exception e) {
        logger.error("请求 {} 时发生未知错误：",request.getRequestURL(), e);
        return Result.error(ErrEnum.APPLICATION_UNKNOWN_ERROR);
    }



}
