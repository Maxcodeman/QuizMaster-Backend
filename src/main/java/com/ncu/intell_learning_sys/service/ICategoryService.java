package com.ncu.intell_learning_sys.service;

import com.ncu.intell_learning_sys.entity.Category;
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
}
