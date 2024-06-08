package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Type;
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
public interface ITypeService extends IService<Type> {

    List<Type> getAllTypes();

    int addType(String typeName);

    int deleteByIds(List<Long> ids);

    Type selectById(Integer id);

    int editById(Integer id, String name);
}
