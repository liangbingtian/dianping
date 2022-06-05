package com.liang.dianping.request;

import lombok.Data;

/**
 * @author liangbingtian
 * @date 2022/06/04 下午5:17
 */
@Data
public class PageQuery {

  private Integer page = 1;

  private Integer size = 20;

}
