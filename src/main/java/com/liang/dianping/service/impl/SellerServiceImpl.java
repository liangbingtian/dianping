package com.liang.dianping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liang.dianping.service.SellerService;
import com.liang.dianping.mapper.SellerMapper;
import com.liang.dianping.model.Seller;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * seller -
 *
 * @author liangbingtian
 */
@Service
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {

  @Override
  public Seller create(Seller seller) {
    return null;
  }

  @Override
  public Seller get(Integer id) {
    return null;
  }

  @Override
  public List<Seller> selectAll() {
    return null;
  }

  @Override
  public Seller changeStatus(Integer id, Integer disabledFlag) {
    return null;
  }
}
