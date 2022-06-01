package com.liang.dianping.common;

/**
 * 通用result
 *
 * @author liangbingtian
 * @date 2022/05/30 下午2:38
 */
public class CommonRes {

  //返回结果
  private String status;

  //若status=success，表明对应的返回json类数据
  private Object data;

  //通用的创建返回对象的方法
  public static CommonRes create(Object result) {
    return create(result, "success");
  }

  public static CommonRes create(Object result, String status) {
    CommonRes res = new CommonRes();
    res.setData(result);
    res.setStatus(status);
    return res;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
