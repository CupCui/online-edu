package com.mycup.edu.handler;

import com.mycup.response.RetVal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Garre
 * @create 2020-04-11 9:30
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RetVal error(Exception e) {
        // 处理异常逻辑代码
        e.printStackTrace();
        return RetVal.error().message("Global Exception");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public RetVal error(ArithmeticException e) {
        // 处理异常逻辑代码
        e.printStackTrace();
        return RetVal.error().message("Arithmetic Exception");
    }

    @ExceptionHandler(EduException.class)
    @ResponseBody
    public RetVal error(EduException e) {
        // 处理异常逻辑代码
        e.printStackTrace();
        return RetVal.error().message(e.getMessage());
    }


}
