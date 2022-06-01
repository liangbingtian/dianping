package com.liang.dianping.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liangbingtian
 * @date 2022/06/01 下午2:55
 */
@Data
public class RegisterReq {

  @NotBlank(message = "手机号不能为空")
  private String telphone;
  @NotBlank(message = "密码不能为空")
  private String password;

  @NotBlank(message = "昵称不能为空")
  private String nickName;

  @NotNull(message = "性别不能为空")
  private Integer gender;
}
