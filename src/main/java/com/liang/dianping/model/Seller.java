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
 * seller - 
 *
 * @author liangbingtian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("seller")
public class Seller {
  @TableId(type = IdType.AUTO)
  private Integer id;

  private String name;

  @TableField(exist = false)
  private Date createTime;

  @TableField(exist = false)
  private Date updateTime;

  private BigDecimal remarkScore;

  private Integer disabledFlag;

}
