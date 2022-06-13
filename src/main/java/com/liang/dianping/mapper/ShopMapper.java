package com.liang.dianping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.dianping.model.Shop;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * shop - 
 *
 * @author liangbingtian
 */
@Repository
public interface ShopMapper extends BaseMapper<Shop> {

  List<Shop> recommend(@Param("longitude") BigDecimal longitude,@Param("latitude") BigDecimal latitude);

}