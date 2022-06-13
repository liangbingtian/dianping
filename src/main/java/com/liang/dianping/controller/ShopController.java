package com.liang.dianping.controller;

import com.liang.dianping.common.BusinessException;
import com.liang.dianping.common.CommonRes;
import com.liang.dianping.common.EmBusinessError;
import com.liang.dianping.model.Shop;
import com.liang.dianping.service.CategoryService;
import com.liang.dianping.service.ShopService;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangbingtian
 * @date 2022/06/05 下午4:01
 */
@RestController("/shop")
@RequestMapping(value = "/shop")
public class ShopController {

  @Autowired
  private ShopService shopService;

  @Autowired
  private CategoryService categoryService;

  //推荐服务
  @GetMapping("/recommend")
  public CommonRes recommend(@RequestParam(name = "longitude") BigDecimal longitude,
      @RequestParam(name = "latitude") BigDecimal latitude)
      throws BusinessException {
    if (longitude == null || latitude == null) {
      throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
    }
    List<Shop> shopList = shopService.recommend(longitude, latitude);
    return CommonRes.create(shopList);
  }


  //搜索服务V1.0
  @RequestMapping("/search")
  @ResponseBody
  public CommonRes search(@RequestParam(name = "longitude") BigDecimal longitude,
      @RequestParam(name = "latitude") BigDecimal latitude,
      @RequestParam(name = "keyword") String keyword) throws BusinessException {
      if (StringUtils.isEmpty(keyword)||longitude==null||latitude==null) {
        throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
      }
      List<Shop> shopList = shopService.search(longitude, latitude, keyword);
      return CommonRes.create(shopList);
  }

}
