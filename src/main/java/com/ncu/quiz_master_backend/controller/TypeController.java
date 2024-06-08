package com.ncu.quiz_master_backend.controller;


import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.entity.Type;
import com.ncu.quiz_master_backend.service.ITypeService;
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

    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Long> ids){
        int columns=iTypeService.deleteByIds(ids);
        log.info("删除的题型数"+columns);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        Type type=iTypeService.selectById(id);
        if(type==null){
            return Result.error("获取题型失败");
        }else{
            log.info("根据id查询到的题型:{}",type);
            return Result.success(type);
        }
    }

    @PutMapping
    public Result editById(@RequestParam Integer id,@RequestParam(required = false) String name){
        int column=iTypeService.editById(id,name);
        log.info("修改的题型数:"+column);
        if(column==1){
            return Result.success();
        }else{
            return Result.error("修改失败");
        }
    }
}
