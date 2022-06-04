package com.liang.dianping.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import sun.misc.BASE64Encoder;

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

  public static String encodeByMd5(String str) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    BASE64Encoder base64Encoder = new BASE64Encoder();
    return base64Encoder.encode(messageDigest.digest(str.getBytes(StandardCharsets.UTF_8)));
  }

}
