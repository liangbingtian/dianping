package com.liang.dianping.service.impl;


import static com.liang.dianping.common.CommonUtil.encodeByMd5;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.dianping.common.BusinessException;
import com.liang.dianping.common.EmBusinessError;
import com.liang.dianping.mapper.UserMapper;
import com.liang.dianping.model.User;
import com.liang.dianping.service.UserService;
import java.security.NoSuchAlgorithmException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * user - 
 *
 * @author liangbingtian
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Override
  @Transactional
  public User register(User registerUser) throws BusinessException, NoSuchAlgorithmException {
    try {
      registerUser.setPassword(encodeByMd5(registerUser.getPassword()));
      save(registerUser);
    }catch (DuplicateKeyException ex) {
      throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
    }
    return registerUser;
  }

  @Override
  public User login(String telephone, String password)
      throws NoSuchAlgorithmException, BusinessException {
    User result = getOne(new LambdaQueryWrapper<User>().eq(User::getTelephone, telephone)
        .eq(User::getPassword, encodeByMd5(telephone)));
    if (result==null) {
      throw new BusinessException(EmBusinessError.LOGIN_FAIL);
    }
    return result;
  }

  @Override
  public Long countAllUsers() {
    return count();
  }


}
