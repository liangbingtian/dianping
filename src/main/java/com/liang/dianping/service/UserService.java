package com.liang.dianping.service;


import com.liang.dianping.common.BusinessException;
import com.liang.dianping.model.User;
import java.security.NoSuchAlgorithmException;

/**
 * user - 
 *
 * @author liangbingtian
 */
public interface UserService {

  User register(User registerUser) throws BusinessException, NoSuchAlgorithmException;

  User login(String telephone, String password) throws NoSuchAlgorithmException, BusinessException;

  Long countAllUsers();

}
