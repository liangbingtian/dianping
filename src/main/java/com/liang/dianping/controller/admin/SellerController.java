package com.liang.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liang.dianping.common.AdminPermission;
import com.liang.dianping.common.BusinessException;
import com.liang.dianping.common.CommonRes;
import com.liang.dianping.model.Seller;
import com.liang.dianping.request.PageQuery;
import com.liang.dianping.request.SellerCreateReq;
import com.liang.dianping.service.SellerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liangbingtian
 * @date 2022/06/04 下午4:23
 */
@Controller
@RequestMapping(value = "/admin/seller")
public class SellerController {

  @Autowired
  private SellerService sellerService;

  //商户列表
  @GetMapping(value = "/index")
  @AdminPermission
  public ModelAndView index(PageQuery pageQuery) {
    //向我们当前线程的threadLocal中设置第几页和查多少个
    PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());

    List<Seller> sellers = sellerService.selectAll();
    PageInfo<Seller> sellerPageInfo = new PageInfo<>(sellers);

    ModelAndView modelAndView = new ModelAndView("/admin/seller/index");
    modelAndView.addObject("data", sellerPageInfo);
    modelAndView.addObject("CONTROLLER_NAME", "seller");
    modelAndView.addObject("ACTION_NAME", "index");
    return modelAndView;
  }

  @GetMapping(value = "/createpage")
  @AdminPermission
  public ModelAndView createPage() {
    ModelAndView modelAndView = new ModelAndView("/admin/seller/create");
    modelAndView.addObject("CONTROLLER_NAME", "seller");
    modelAndView.addObject("ACTION_NAME", "create");
    return modelAndView;
  }

  //不加requestBody就是用的表单的方式
  @PostMapping(value = "/create")
  @AdminPermission
  public String create(@Valid SellerCreateReq createReq) {
    Seller seller = new Seller();
    seller.setName(createReq.getName());
    sellerService.create(seller);
    return "redirect:/admin/seller/index";
  }

  @RequestMapping(value = "/down", method = RequestMethod.POST)
  @ResponseBody
  @AdminPermission
  public CommonRes down(@RequestParam(value = "id") Integer id) throws BusinessException {
    Seller seller = sellerService.changeStatus(id, 1);
    return CommonRes.create(seller);
  }

  @RequestMapping(value = "/up", method = RequestMethod.POST)
  @ResponseBody
  @AdminPermission
  public CommonRes up(@RequestParam(value = "id") Integer id) throws BusinessException {
    Seller seller = sellerService.changeStatus(id, 0);
    return CommonRes.create(seller);
  }

}
