package com.liang.dianping.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.dianping.common.BusinessException;
import com.liang.dianping.common.EmBusinessError;
import com.liang.dianping.model.Category;
import com.liang.dianping.model.Seller;
import com.liang.dianping.service.CategoryService;
import com.liang.dianping.service.SellerService;
import com.liang.dianping.service.ShopService;
import com.liang.dianping.mapper.ShopMapper;
import com.liang.dianping.model.Shop;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * shop -
 *
 * @author liangbingtian
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private SellerService sellerService;

  @Override
  @Transactional
  public Shop create(Shop shop) throws BusinessException {
    //校验商户
    Seller seller = sellerService.get(shop.getSellerId());
    if (seller == null) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商户不存在");
    }

    if (seller.getDisabledFlag().equals(1)) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商户已禁用");
    }
    //校验类目
    Category category = categoryService.get(shop.getCategoryId());
    if (category == null) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "类目不存在");
    }
    save(shop);
    return shop;
  }

  @Override
  public Shop get(Integer id) {
    Shop shop = get(id);
    if (shop == null) {
      return null;
    }
    shop.setSellerModel(sellerService.get(shop.getSellerId()));
    shop.setCategoryModel(categoryService.get(shop.getCategoryId()));
    return shop;
  }

  @Override
  public List<Shop> selectAll() {
    List<Shop> list = list();
    list.forEach(data -> {
      data.setSellerModel(sellerService.get(data.getSellerId()));
      data.setCategoryModel(categoryService.get(data.getCategoryId()));
    });
    return list;
  }

  @Override
  public Long countAllShops() {
    return count();
  }

  @Override
  public List<Shop> recommend(BigDecimal longitude, BigDecimal latitude) {
    return list(Wrappers.lambdaQuery(Shop.class).eq(Shop::getLongitude, longitude)
        .eq(Shop::getLatitude, latitude).orderByAsc(Shop::getId));
  }

  @Override
  public List<Shop> search(BigDecimal longitude, BigDecimal latitude, String keyword) {
    return null;
  }


}
