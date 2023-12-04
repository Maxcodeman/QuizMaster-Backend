package com.ncu.intell_learning_sys.service.impl;

import com.ncu.intell_learning_sys.entity.Log;
import com.ncu.intell_learning_sys.mapper.LogMapper;
import com.ncu.intell_learning_sys.service.ILogService;
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
