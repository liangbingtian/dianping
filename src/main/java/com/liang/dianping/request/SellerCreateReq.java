package com.liang.dianping.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author liangbingtian
 * @date 2022/06/04 下午4:43
 */
@Data
public class SellerCreateReq {

  @NotBlank(message = "手机号不能为空")
  private String name;
}
