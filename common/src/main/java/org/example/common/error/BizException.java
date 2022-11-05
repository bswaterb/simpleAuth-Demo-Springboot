package org.example.common.error;

/**
 * @author bswaterb
 * @date 2022-11-04 19:00:05
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected int errCode;
    protected String errMessage;

    public BizException() {
        super();
    }

    public BizException(BaseErrInterface errInfo) {
        super(String.valueOf(errInfo.getErrCode()));
        this.errCode = errInfo.getErrCode();
        this.errMessage = errInfo.getErrMessage();
    }

    public BizException(BaseErrInterface errInfo, Throwable cause) {
        super(String.valueOf(errInfo.getErrCode()), cause);
        this.errCode = errInfo.getErrCode();
        this.errMessage = errInfo.getErrMessage();
    }

    public BizException(int errCode, String errMessage) {
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public BizException(int errCode, String errMessage, Throwable cause) {
        super(String.valueOf(errCode), cause);
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public int getErrCode() {
        return this.errCode;
    }

    public String getErrMessage() {
        return this.errMessage;
    }

    public Throwable fillInStackTrace() {
        return this;
    }
}

