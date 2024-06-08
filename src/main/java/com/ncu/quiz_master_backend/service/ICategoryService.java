package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
public interface ICategoryService extends IService<Category> {

    List<Category> getAllCategories();

    Integer addCategory(String categoryName);

    int deleteByIds(List<Long> ids);

    Category selectById(Integer id);

    int editById(Integer id, String name);
}
