package com.liang.dianping.controller.admin;

import com.liang.dianping.common.AdminPermission;
import com.liang.dianping.common.BusinessException;
import com.liang.dianping.common.CommonUtil;
import com.liang.dianping.common.EmBusinessError;
import com.liang.dianping.service.CategoryService;
import com.liang.dianping.service.SellerService;
import com.liang.dianping.service.ShopService;
import com.liang.dianping.service.UserService;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liangbingtian
 * @date 2022/06/02 下午4:50
 */
@Controller
@RequestMapping(value = "/admin/admin")
public class AdminController {

  @Autowired
  private HttpServletRequest servletRequest;

  @Autowired
  private UserService userService;

  @Autowired
  private ShopService shopService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private SellerService sellerService;

  @Value("${admin.email}")
  private String email;

  @Value("${admin.encryptPassword}")
  private String encryptPassword;

  public static final String CURRENT_ADMIN_SESSION = "currentAdminSession";

  /**
   * 运营控制台的首页，必须要登录才可访问
   * @return
   */
  @AdminPermission
  @GetMapping(value = "/index")
  public ModelAndView index() {
    ModelAndView modelAndView = new ModelAndView("/admin/admin/index");
    modelAndView.addObject("userCount", userService.countAllUsers());
    modelAndView.addObject("shopCount", shopService.countAllShops());
    modelAndView.addObject("categoryCount", categoryService.countAllCategories());
    modelAndView.addObject("sellerCount", sellerService.countSellers());
    modelAndView.addObject("CONTROLLER_NAME", "admin");
    modelAndView.addObject("ACTION_NAME", "index");
    return modelAndView;
  }

  @GetMapping(value = "/loginpage")
  public ModelAndView loginpage() {
    return new ModelAndView("/admin/admin/login");
  }

  @PostMapping(value = "/login")
  public String login(@RequestParam(name = "email") String email,
      @RequestParam(name = "password") String password)
      throws BusinessException, NoSuchAlgorithmException {
    if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户名或密码不能为空");
    }
    if (email.equals(this.email) && CommonUtil.encodeByMd5(password).equals(this.encryptPassword)) {
      //登录成功
      servletRequest.getSession().setAttribute(CURRENT_ADMIN_SESSION, email);
      return "redirect:/admin/admin/index";
    } else {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户名或密码错误");
    }
  }
}
