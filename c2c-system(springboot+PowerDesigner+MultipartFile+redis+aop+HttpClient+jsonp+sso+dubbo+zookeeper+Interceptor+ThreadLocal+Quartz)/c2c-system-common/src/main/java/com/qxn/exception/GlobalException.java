package com.qxn.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.qxn.vo.SysResult;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestControllerAdvice//对controller异常有效
public class GlobalException {
    //只对运行时异常有效
    @ExceptionHandler(RuntimeException.class)
    public SysResult exception(Throwable throwable) {
        throwable.printStackTrace();//详细报错
        log.info(throwable.getMessage());//错误名
        return SysResult.fail("调用失败");
    }
}
