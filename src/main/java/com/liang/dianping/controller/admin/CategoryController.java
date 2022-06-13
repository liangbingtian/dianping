package com.liang.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liang.dianping.common.AdminPermission;
import com.liang.dianping.common.BusinessException;
import com.liang.dianping.model.Category;
import com.liang.dianping.request.CategoryCreateReq;
import com.liang.dianping.request.PageQuery;
import com.liang.dianping.service.CategoryService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liangbingtian
 * @date 2022/06/05 上午10:56
 */
@Controller("/admin/category")
@RequestMapping("/admin/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  //商户列表
  @GetMapping(value = "/index")
  @AdminPermission
  public ModelAndView index(PageQuery pageQuery) {
    //向我们当前线程的threadLocal中设置第几页和查多少个
    PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());

    List<Category> categoryList = categoryService.selectAll();
    PageInfo<Category> categoryPageInfo = new PageInfo<>(categoryList);

    ModelAndView modelAndView = new ModelAndView("/admin/category/index");
    modelAndView.addObject("data", categoryPageInfo);
    modelAndView.addObject("CONTROLLER_NAME", "category");
    modelAndView.addObject("ACTION_NAME", "index");
    return modelAndView;
  }

  @GetMapping(value = "/createpage")
  @AdminPermission
  public ModelAndView createPage() {
    ModelAndView modelAndView = new ModelAndView("/admin/category/create");
    modelAndView.addObject("CONTROLLER_NAME", "category");
    modelAndView.addObject("ACTION_NAME", "create");
    return modelAndView;
  }

  //不加requestBody就是用的表单的方式
  @PostMapping(value = "/create")
  @AdminPermission
  public String create(@Valid CategoryCreateReq createReq) throws BusinessException {
    Category category = new Category();
    category.setName(createReq.getName());
    category.setIconUrl(createReq.getIconUrl());
    categoryService.create(category);
    return "redirect:/admin/category/index";
  }

}
