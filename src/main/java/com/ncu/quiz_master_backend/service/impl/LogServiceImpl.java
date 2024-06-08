package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.Log;
import com.ncu.quiz_master_backend.mapper.LogMapper;
import com.ncu.quiz_master_backend.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}