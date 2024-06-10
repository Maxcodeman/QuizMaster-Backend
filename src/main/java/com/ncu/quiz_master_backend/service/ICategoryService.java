package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ncu.quiz_master_backend.entity.PageBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
public interface ICategoryService {


    PageBean listAll(Integer page, Integer pageSize, String categoryName);

    void removeById(Integer id);
}
