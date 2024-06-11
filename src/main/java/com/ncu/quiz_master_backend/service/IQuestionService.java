package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Question;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IQuestionService{


    PageBean listAll(Integer page, Integer pageSize, String questionDesc, Integer categoryId, Integer type);

    void removeById(List<Integer> ids);

    void addOne(Question question);

    Question getById(Integer id);

    void modify(Question question);

    void upload(MultipartFile file) throws IOException;
}
