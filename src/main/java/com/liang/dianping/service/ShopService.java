package com.liang.dianping.service;

import com.liang.dianping.common.BusinessException;
import com.liang.dianping.model.Shop;
import java.math.BigDecimal;
import java.util.List;

/**
 * shop - 
 *
 * @author liangbingtian
 */
public interface ShopService {
  Shop create(Shop shop) throws BusinessException;
  Shop get(Integer id);
  List<Shop> selectAll();
  Long countAllShops();
  List<Shop> recommend(BigDecimal longitude, BigDecimal latitude);
  List<Shop> search(BigDecimal longitude, BigDecimal latitude, String keyword);
}
