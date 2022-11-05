package org.example.common.result;

import lombok.Data;
import org.example.common.error.BaseErrInterface;

/**
 * @author bswaterb
 * @date 2022-11-05 11:07:19
 */
@Data
public class Result<E> {
    int code;
    String message;
    E result;

    public Result(int resultCode,String message,E result){
        this.result = result;
        this.code = resultCode;
        this.message = message;
    }

    public static Result success(){
        return success(null);
    }

    public static Result success(Object data){
        Result result = new Result(200,"请求成功",data);
        return result;
    }

    public static Result error(BaseErrInterface errInfo){
        Result result = new Result(errInfo.getErrCode(),errInfo.getErrMessage(), null);
        return result;
    }

    public static Result error(int code,String message){
        Result result = new Result(code,message,null);
        return result;
    }

    public static Result error(String message){
        Result result = new Result(-1, message,null);
        return result;
    }

}
