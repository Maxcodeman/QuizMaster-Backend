package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.Category;
import com.ncu.quiz_master_backend.mapper.CategoryMapper;
import com.ncu.quiz_master_backend.service.ICategoryService;
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

    @Override
    public int deleteByIds(List<Long> ids) {
        return categoryMapper.deleteBatchIds(ids);
    }

    @Override
    public Category selectById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public int editById(Integer id, String name) {
        Category category=categoryMapper.selectById(id);
        if(name!=null&&!name.equals(category.getCategoryName())){
            category.setCategoryName(name);
        }
        return categoryMapper.updateById(category);
    }

}
