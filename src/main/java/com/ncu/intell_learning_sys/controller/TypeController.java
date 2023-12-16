package com.ncu.intell_learning_sys.controller;


import com.ncu.intell_learning_sys.entity.Result;
import com.ncu.intell_learning_sys.entity.Type;
import com.ncu.intell_learning_sys.service.ITypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Slf4j
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private ITypeService iTypeService;

    @GetMapping
    public Result getAllTypes(){
        List<Type> typeList= iTypeService.getAllTypes();
        log.info("获取所有题型:{}",typeList);
        return Result.success(typeList);
    }
}
