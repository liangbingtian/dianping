package com.liang.dianping.controller;

import com.liang.dianping.model.Seller;
import com.liang.dianping.service.SellerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liangbingtian
 * @date 2022/06/02 下午4:29
 */
@RestController
@RequestMapping("/admin/seller")
public class SellerController {

  @Autowired
  private SellerService sellerService;

  @RequestMapping("/index")
  public ModelAndView index() {
    List<Seller> sellerList = sellerService.selectAll();
    ModelAndView modelAndView = new ModelAndView("/admin/seller/index.html");
    modelAndView.addObject("data", sellerList);
    modelAndView.addObject("CONTROLLER_NAME", "seller");
    modelAndView.addObject("ACTION_NAME", "index");
    return modelAndView;
  }

}
