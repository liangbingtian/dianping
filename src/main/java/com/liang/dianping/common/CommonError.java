package com.liang.dianping.common;

/**
 * 通用的返回错误
 *
 * @author liangbingtian
 * @date 2022/05/30 下午3:22
 */
public class CommonError {
  //错误码
  private Integer errorCode;
  //错误描述
  private String errorMsg;

  public CommonError(Integer errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  public CommonError(EmBusinessError error) {
    this(error.getErrorCode(), error.getErrorMsg());
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
}
