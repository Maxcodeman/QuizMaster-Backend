package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.Type;
import com.ncu.quiz_master_backend.mapper.TypeMapper;
import com.ncu.quiz_master_backend.service.ITypeService;
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

    @Override
    public int addType(String typeName) {
        Type type=new Type();
        type.setTypeName(typeName);

        return typeMapper.insert(type);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        return typeMapper.deleteBatchIds(ids);
    }

    @Override
    public Type selectById(Integer id) {
        return typeMapper.selectById(id);
    }

    @Override
    public int editById(Integer id, String name) {
        Type type=typeMapper.selectById(id);
        if(name!=null&&!name.equals(type.getTypeName())){
            type.setTypeName(name);
        }
        return typeMapper.updateById(type);
    }
}
