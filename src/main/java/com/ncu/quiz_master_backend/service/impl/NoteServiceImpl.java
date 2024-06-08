package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.Note;
import com.ncu.quiz_master_backend.mapper.NoteMapper;
import com.ncu.quiz_master_backend.service.INoteService;
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
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements INoteService {

}
