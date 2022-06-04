package com.liang.dianping.controller;

import com.liang.dianping.common.BusinessException;
import com.liang.dianping.common.CommonRes;
import com.liang.dianping.common.CommonUtil;
import com.liang.dianping.common.EmBusinessError;
import com.liang.dianping.model.User;
import com.liang.dianping.request.LoginReq;
import com.liang.dianping.request.RegisterReq;
import com.liang.dianping.service.UserService;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangbingtian
 * @date 2022/06/01 下午2:51
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

  public static final String CURRENT_USER_SESSION = "currentUserSession";

  @Autowired
  private UserService userService;

  @Autowired
  private HttpServletRequest httpServletRequest;

  @PostMapping(value = "/register")
  public CommonRes register(@Valid @RequestBody RegisterReq registerReq)
      throws NoSuchAlgorithmException, BusinessException {
    User user = new User();
    BeanUtils.copyProperties(registerReq, user);
    User registerUser = userService.register(user);
    return CommonRes.create(registerUser);
  }


  @PostMapping(value = "/login")
  public CommonRes login(@RequestBody @Valid LoginReq loginReq)
      throws BusinessException, NoSuchAlgorithmException {
    User user = userService.login(loginReq.getTelephone(), loginReq.getPassword());
    httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION, user);
    return CommonRes.create(null);
  }

  @GetMapping(value = "/logout")
  public CommonRes logout() {
    httpServletRequest.getSession().invalidate();
    return CommonRes.create(null);
  }

  @GetMapping(value = "/get-current-user")
  public CommonRes getCurrentUser() {
    User user = (User) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
    return CommonRes.create(user);
  }

}
