package com.ncu.intell_learning_sys.controller;


import com.ncu.intell_learning_sys.entity.Result;
import com.ncu.intell_learning_sys.entity.Type;
import com.ncu.intell_learning_sys.service.ITypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Result addType(@RequestParam String typeName){
        int column=iTypeService.addType(typeName);
        if(column==1){
            log.info("添加题型成功");
            return Result.success();
        }else{
            log.info("添加题型失败");
            return Result.error("添加题型失败");
        }
    }

}
