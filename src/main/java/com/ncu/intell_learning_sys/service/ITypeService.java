package com.ncu.intell_learning_sys.service;

import com.ncu.intell_learning_sys.entity.Type;
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
}
