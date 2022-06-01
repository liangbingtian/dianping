package com.liang.dianping.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 *
 * @author liangbingtian
 * @date 2022/05/30 下午4:24
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public CommonRes doError(HttpServletRequest request
      , HttpServletResponse response
      , Exception ex) {
      if (ex instanceof BusinessException) {
        return CommonRes.create(((BusinessException)ex).getCommonError(), "fail");
      } else if (ex instanceof NoHandlerFoundException) {
        CommonError commonError = new CommonError(EmBusinessError.NO_HANDLER_FOUND);
        return CommonRes.create(commonError, "fail");
      } else {
        CommonError commonError = new CommonError(EmBusinessError.UNKNOWN_ERROR);
        return CommonRes.create(commonError, "fail");
      }
  }
}
