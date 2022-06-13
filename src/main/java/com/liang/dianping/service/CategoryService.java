package com.liang.dianping.service;


import com.liang.dianping.common.BusinessException;
import com.liang.dianping.model.Category;
import java.util.List;

/**
 * category - 
 *
 * @author liangbingtian
 */
public interface CategoryService {

  Category create(Category category) throws BusinessException;
  Category get(Integer id);
  List<Category> selectAll();
  Long countAllCategories();

}
