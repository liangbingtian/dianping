package com.liang.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liang.dianping.common.AdminPermission;
import com.liang.dianping.common.BusinessException;
import com.liang.dianping.model.Shop;
import com.liang.dianping.request.PageQuery;
import com.liang.dianping.request.ShopCreateReq;
import com.liang.dianping.service.ShopService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liangbingtian
 * @date 2022/06/05 下午3:25
 */
@Controller("/admin/shop")
@RequestMapping(value = "/admin/shop")
public class ShopController {

  @Autowired
  private ShopService shopService;

  //商户列表
  @GetMapping(value = "/index")
  @AdminPermission
  public ModelAndView index(PageQuery pageQuery) {
    //向我们当前线程的threadLocal中设置第几页和查多少个
    PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());

    List<Shop> shopList = shopService.selectAll();
    PageInfo<Shop> shopPageInfo = new PageInfo<>(shopList);

    ModelAndView modelAndView = new ModelAndView("/admin/shop/index");
    modelAndView.addObject("data", shopPageInfo);
    modelAndView.addObject("CONTROLLER_NAME", "shop");
    modelAndView.addObject("ACTION_NAME", "index");
    return modelAndView;
  }

  @GetMapping(value = "/createpage")
  @AdminPermission
  public ModelAndView createPage() {
    ModelAndView modelAndView = new ModelAndView("/admin/shop/create");
    modelAndView.addObject("CONTROLLER_NAME", "shop");
    modelAndView.addObject("ACTION_NAME", "create");
    return modelAndView;
  }

  //不加requestBody就是用的表单的方式
  @PostMapping(value = "/create")
  @AdminPermission
  public String create(@Valid ShopCreateReq createReq) throws BusinessException {
    Shop shop = new Shop();
    BeanUtils.copyProperties(createReq, shop);
    shopService.create(shop);
    return "redirect:/admin/shop/index";
  }

}
