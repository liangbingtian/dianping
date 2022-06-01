package com.liang.dianping.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * user - 
 *
 * @author liangbingtian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {

  @TableId(type = IdType.AUTO)
  private Integer id;

  @TableField(exist = false)
  private Date createTime;

  @TableField(exist = false)
  private Date updateTime;

  private String telphone;

  private String password;

  private String nickName;

  private Integer gender;

}
