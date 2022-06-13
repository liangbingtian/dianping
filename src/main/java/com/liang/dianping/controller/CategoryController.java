package com.liang.dianping.controller;

import com.liang.dianping.common.CommonRes;
import com.liang.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangbingtian
 * @date 2022/06/05 上午11:13
 */
@RequestMapping("/category")
@RestController("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @RequestMapping(value = "/list")
  public CommonRes list() {
    return CommonRes.create(categoryService.selectAll());
  }
}
