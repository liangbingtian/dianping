package com.liang.dianping.service;


import com.liang.dianping.common.BusinessException;
import com.liang.dianping.model.Seller;
import java.util.List;

/**
 * seller - 
 *
 * @author liangbingtian
 */
public interface SellerService {

  Seller create(Seller seller);
  Seller get(Integer id);
  List<Seller> selectAll();
  Seller changeStatus(Integer id, Integer disabledFlag) throws BusinessException;
  Long countSellers();

}
