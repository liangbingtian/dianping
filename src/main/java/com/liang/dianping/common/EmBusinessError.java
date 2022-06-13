package com.liang.dianping.common;

/**
 * @author liangbingtian
 * @date 2022/05/30 下午3:25
 */
public enum EmBusinessError {

  //通用的错误类型
  NO_OBJECT_FOUND(10001, "请求对象不存在"),
  UNKNOWN_ERROR(10002, "未知错误"),
  NO_HANDLER_FOUND(10003, "找不到执行路径"),
  PARAMETER_VALIDATION_ERROR(10004, "请求参数校验失败"),
  //用户服务错误类型
  REGISTER_DUP_FAIL(20001, "注册失败，用户已存在"),
  LOGIN_FAIL(20002, "手机号或密码错误"),
  //admin相关错误
  ADMIN_SHOULD_LOGIN(30001, "管理员需要先登录"),

  //品类相关错误
  CATEGORY_NAME_DUPLICATED(40001, "品类名称已存在");

  private Integer errorCode;

  private String errorMsg;

  EmBusinessError(Integer errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  EmBusinessError(EmBusinessError emBusinessError) {
    this.errorCode = emBusinessError.getErrorCode();
    this.errorMsg = emBusinessError.getErrorMsg();
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
