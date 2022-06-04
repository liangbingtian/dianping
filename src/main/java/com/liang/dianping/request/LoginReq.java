package com.liang.dianping.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author liangbingtian
 * @date 2022/06/01 下午4:39
 */
@Data
public class LoginReq {

  @NotBlank(message = "手机号不能为空")
  private String telephone;
  @NotBlank(message = "密码不能为空")
  private String password;

}
