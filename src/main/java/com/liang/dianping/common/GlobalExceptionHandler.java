package com.liang.dianping.common;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
@Slf4j
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
        log.error(ex.getMessage());
        ex.printStackTrace();
        return CommonRes.create(ex.getMessage(), "fail");
      }
  }

  @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
  public CommonRes handlerValidatorException(Exception ex) {
    log.error(ex.getMessage());
    BindingResult bindingResult = null;
    if (ex instanceof BindException) {
      bindingResult = ((BindException) ex).getBindingResult();
    }
    if (ex instanceof MethodArgumentNotValidException) {
      bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
    }
    if (bindingResult==null) {
      CommonError commonError = new CommonError(EmBusinessError.PARAMETER_VALIDATION_ERROR);
      return CommonRes.create(commonError, "fail");
    }
    String msg = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(
        Collectors.joining(";"));
    CommonError commonError = new CommonError(EmBusinessError.PARAMETER_VALIDATION_ERROR);
    commonError.setErrorMsg(msg);
    return CommonRes.create(commonError, "fail");
  }
}
