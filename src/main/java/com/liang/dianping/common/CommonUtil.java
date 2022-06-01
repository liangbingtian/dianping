package com.liang.dianping.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author liangbingtian
 * @date 2022/06/01 下午3:15
 */
public class CommonUtil {

  public static String processErrorString(BindingResult bindingResult) {
    if (!bindingResult.hasErrors()) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      sb.append(fieldError.getDefaultMessage()).append(",");
    }
    return sb.substring(0, sb.length()-1);
  }

}
