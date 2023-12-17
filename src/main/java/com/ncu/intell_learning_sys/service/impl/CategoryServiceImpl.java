package com.ncu.intell_learning_sys.service.impl;

import com.ncu.intell_learning_sys.entity.Category;
import com.ncu.intell_learning_sys.mapper.CategoryMapper;
import com.ncu.intell_learning_sys.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.selectList(null);
    }

    @Override
    public Integer addCategory(String categoryName) {
        Category category=new Category();
        category.setCategoryName(categoryName);

        return categoryMapper.insert(category);
    }
}
