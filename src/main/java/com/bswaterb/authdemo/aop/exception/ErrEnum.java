package com.bswaterb.authdemo.aop.exception;

/**
 * @author bswaterb
 * @date 2022-11-04 18:46:23
 */
public enum ErrEnum implements BaseErrInterface{
    NULL_POINTER(400, "空指针异常"),
    CONTENT_NOT_MATCH(401, "传参有误"),
    REQUEST_METHOD_ERROR(402, "请求方式有误"),
    APPLICATION_UNKNOWN_ERROR(499, "未知异常"),
    TOKEN_EXPIRED(500, "Token 已过期"),
    TOKEN_INVALID(501, "Token 无效"),
    TOKEN_UNKNOWN_ERROR(599, "Token 校验出现未知错误"),
    USER_AUTH_NOT_PASS(600, "用户权限不足，请求接口失败");

    private int errCode;
    private String errMessage;

    ErrEnum(int errCode, String errMessage) {
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMessage() {
        return errMessage;
    }

}
