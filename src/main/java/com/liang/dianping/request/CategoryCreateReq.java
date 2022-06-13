package com.liang.dianping.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liangbingtian
 * @date 2022/06/05 上午11:03
 */
@Data
public class CategoryCreateReq {
  @NotBlank(message = "名字不能为空")
  private String name;
  @NotBlank(message = "iconUrl不能为空")
  private String iconUrl;
  @NotNull(message = "权重不能为空")
  private Integer sort;

}
