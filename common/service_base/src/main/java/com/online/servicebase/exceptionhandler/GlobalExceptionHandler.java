package com.online.servicebase.exceptionhandler;


import com.online.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    //全局 异常
//    @ExceptionHandler(Exception.class)
//    @ResponseBody  //返回数据 返回体
//    public R error(Exception e){
//        e.printStackTrace();
//        return R.error().message("执行了全局异常处理..");
//    }

    //自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody  //返回数据 返回体
    public R error(GuliException e){
        e.printStackTrace();
//        return R.error().message("执行了全局异常处理..");
        return  R.error().code(e.getCode()).message(e.getMsg());
    }
}
