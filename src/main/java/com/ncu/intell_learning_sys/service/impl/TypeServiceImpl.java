package com.ncu.intell_learning_sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ncu.intell_learning_sys.entity.Type;
import com.ncu.intell_learning_sys.mapper.TypeMapper;
import com.ncu.intell_learning_sys.service.ITypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getAllTypes() {
        return typeMapper.selectList(null);
    }
}
