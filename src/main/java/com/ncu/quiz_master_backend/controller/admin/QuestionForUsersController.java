package com.ncu.quiz_master_backend.controller.admin;

import com.ncu.quiz_master_backend.entity.FavoriteQuestion;
import com.ncu.quiz_master_backend.entity.QuestionForUsers;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.entity.WrongQuestion;
import com.ncu.quiz_master_backend.service.QuestionForUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class QuestionForUsersController {

    @Autowired
    private QuestionForUsersService questionForUsersService;
    @GetMapping("/questions/categories")
    public Result getAllByCategoryId(@RequestParam Integer categoryId,@RequestParam Integer userId){
        log.info("获取分类ID为"+categoryId+"的题目列表");
        List<QuestionForUsers> questionForUsersList=questionForUsersService.getAllByCategoryId(categoryId,userId);
        log.info(questionForUsersList.toString());
        log.info(Result.success(questionForUsersList).toString());
        return Result.success(questionForUsersList);
    }

    @GetMapping("/favorites")
    public Result checkFavoriteExist(@RequestParam Integer questionId,@RequestParam Integer userId){
        log.info("检查收藏记录是否存在");
        int i= questionForUsersService.checkFavoriteExist(questionId,userId);
        return Result.success(i);
    }
    @PutMapping("/favorites")
    public Result modifyFavoriteState(@RequestBody FavoriteQuestion favoriteQuestion){
        log.info("修改收藏状态");
        log.info(favoriteQuestion.toString());
        questionForUsersService.modifyFavoriteState(favoriteQuestion);
        return Result.success(null);
    }

    @PostMapping("/favorites")
    public Result addFavorite(@RequestBody FavoriteQuestion favoriteQuestion){
        questionForUsersService.addFavorite(favoriteQuestion);
        return Result.success(null);
    }
    @GetMapping("/wrongs")
    public Result checkWrongExist(@RequestParam Integer questionId,@RequestParam Integer userId){
        log.info("检查错题记录是否存在");
        int i= questionForUsersService.checkWrongExist(questionId,userId);
        return Result.success(i);
    }
    @PutMapping("/wrongs")
    public Result modifyWrongCount(@RequestBody WrongQuestion wrongQuestion){
        log.info("增加错误次数");
        log.info(wrongQuestion.toString());
        questionForUsersService.modifyWrongCount(wrongQuestion);
        return Result.success(null);
    }

    @PostMapping("/wrongs")
    public Result addWrong(@RequestBody WrongQuestion wrongQuestion){
        log.info("增加错题记录");
        questionForUsersService.addWrong(wrongQuestion);
        return Result.success(null);
    }

    @GetMapping("/questions/favorites")
    public Result getFavourByUserId(@RequestParam Integer userId){
        log.info("根据用户ID{}查询用户收藏：",userId);
        List<QuestionForUsers> list = questionForUsersService.getFavourByUserId(userId);
        return Result.success(list);
    }

    @GetMapping("/questions/wrongs")
    public Result getWrongByUserId(@RequestParam Integer userId){
        log.info("根据用户ID{}查询用户错题：",userId);
        List<QuestionForUsers> list = questionForUsersService.getWrongByUserId(userId);
        return Result.success(list);
    }
}
