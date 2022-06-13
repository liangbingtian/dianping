package com.liang.dianping.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.dianping.common.BusinessException;
import com.liang.dianping.common.EmBusinessError;
import com.liang.dianping.service.CategoryService;
import com.liang.dianping.mapper.CategoryMapper;
import com.liang.dianping.model.Category;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * category -
 *
 * @author liangbingtian
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements
    CategoryService {

  @Override
  @Transactional
  public Category create(Category category) throws BusinessException {
    try {
      save(category);
    } catch (DuplicateKeyException e) {
      throw new BusinessException(EmBusinessError.CATEGORY_NAME_DUPLICATED);
    }
    return category;
  }

  @Override
  public Category get(Integer id) {
    return getById(id);
  }

  @Override
  public List<Category> selectAll() {
    return list(Wrappers.lambdaQuery(Category.class).orderByDesc(Category::getSort)
        .orderByAsc(Category::getId));
  }

  @Override
  public Long countAllCategories() {
   return count();
  }


}
