package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
public interface IQuestionService{


    PageBean listAll(Integer page, Integer pageSize, String questionDesc, Integer categoryId, Integer type);

    void removeById(List<Integer> ids);

    List<Question> addOne(Question question);

    Question getById(Integer id);

    void modify(Question question);

    void upload(MultipartFile file);
}
