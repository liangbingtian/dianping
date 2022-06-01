package com.liang.dianping.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.dianping.common.BusinessException;
import com.liang.dianping.common.EmBusinessError;
import com.liang.dianping.mapper.UserMapper;
import com.liang.dianping.model.User;
import com.liang.dianping.service.UserService;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

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


  private String encodeByMd5(String str) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    BASE64Encoder base64Encoder = new BASE64Encoder();
    return base64Encoder.encode(messageDigest.digest(str.getBytes(StandardCharsets.UTF_8)));
  }
}
