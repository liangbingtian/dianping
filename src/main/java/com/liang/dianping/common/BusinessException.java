package com.liang.dianping.common;

/**
 * 业务异常
 *
 * @author liangbingtian
 * @date 2022/05/30 下午3:32
 */
public class BusinessException extends Exception{
  private CommonError commonError;

  public BusinessException(EmBusinessError businessError) {
    super();
    this.commonError = new CommonError(businessError);
  }

  public BusinessException(EmBusinessError businessError, String errMsg) {
    super();
    this.commonError = new CommonError(businessError);
    this.commonError.setErrorMsg(errMsg);
  }

  public CommonError getCommonError() {
    return commonError;
  }

  public void setCommonError(CommonError commonError) {
    this.commonError = commonError;
  }
}
