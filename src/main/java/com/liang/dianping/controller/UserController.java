package com.liang.dianping.controller;

import com.liang.dianping.common.BusinessException;
import com.liang.dianping.common.CommonRes;
import com.liang.dianping.common.CommonUtil;
import com.liang.dianping.common.EmBusinessError;
import com.liang.dianping.model.User;
import com.liang.dianping.request.RegisterReq;
import com.liang.dianping.service.UserService;
import java.security.NoSuchAlgorithmException;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangbingtian
 * @date 2022/06/01 下午2:51
 */
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/register")
  public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult)
      throws NoSuchAlgorithmException, BusinessException {
    if (bindingResult.hasErrors()) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
    }
    User user = new User();
    BeanUtils.copyProperties(registerReq, user);
    User registerUser = userService.register(user);
    return CommonRes.create(registerUser);
  }

}
