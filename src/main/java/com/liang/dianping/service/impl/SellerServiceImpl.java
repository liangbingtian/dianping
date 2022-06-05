package com.liang.dianping.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liang.dianping.common.BusinessException;
import com.liang.dianping.common.EmBusinessError;
import com.liang.dianping.service.SellerService;
import com.liang.dianping.mapper.SellerMapper;
import com.liang.dianping.model.Seller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * seller -
 *
 * @author liangbingtian
 */
@Service
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {

  @Override
  @Transactional
  public Seller create(Seller seller) {
    save(seller);
    return seller;
  }

  @Override
  public Seller get(Integer id) {
    return null;
  }

  @Override
  public List<Seller> selectAll() {
    return list();
  }

  @Override
  public Seller changeStatus(Integer id, Integer disabledFlag) throws BusinessException {
    Seller updateSeller = getById(id);
    if (updateSeller==null) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
    }
    updateSeller.setDisabledFlag(disabledFlag);
    updateById(updateSeller);
    return updateSeller;
  }
}
