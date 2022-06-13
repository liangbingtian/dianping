package com.liang.dianping.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liangbingtian
 * @date 2022/06/05 下午3:30
 */
@Data
public class ShopCreateReq {

  @NotBlank(message = "服务名不能为空")
  private String name;

  @NotNull
  private Integer pricePerMan;

  @NotNull
  private BigDecimal latitude;

  @NotNull
  private BigDecimal longitude;

  @NotNull
  private Integer categoryId;


  private String tags;

  @NotBlank
  private String startTime;

  @NotBlank
  private String endTime;

  @NotBlank
  private String address;

  @NotNull
  private Integer sellId;

  @NotBlank
  private String iconUrl;

}
