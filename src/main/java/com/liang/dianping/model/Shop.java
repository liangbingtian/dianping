package com.liang.dianping.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;

/**
 * shop - 
 *
 * @author liangbingtian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("shop")
public class Shop {

  @TableId(type = IdType.AUTO)
  private Integer id;

  @TableField(exist = false)
  private Date createTime;

  @TableField(exist = false)
  private Date updateTime;

  private String name;

  private BigDecimal remarkScore;

  private Integer pricePerMan;

  private BigDecimal latitude;

  private BigDecimal longitude;

  private Integer categoryId;

  @TableField(exist = false)
  private Category categoryModel;

  private String tags;

  private String startTime;

  private String endTime;

  private String address;

  private Integer sellerId;

  @TableField(exist = false)
  private Seller sellerModel;

  private String iconUrl;

  @TableField(exist = false)
  private Integer distance;

}
