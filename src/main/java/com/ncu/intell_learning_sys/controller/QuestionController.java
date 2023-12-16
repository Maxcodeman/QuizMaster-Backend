package com.ncu.intell_learning_sys.controller;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ncu.intell_learning_sys.entity.Question;
import com.ncu.intell_learning_sys.entity.Result;
import com.ncu.intell_learning_sys.service.IQuestionService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private IQuestionService iQuestionService;

    @GetMapping
    public Result selectById(@RequestParam Long id){
        Question question=iQuestionService.selectById(id);
        if(question==null){
            return Result.error("获取题目失败");
        }else {
            log.info("根据id查询到的题目:{}",question);
            return Result.success(question);
        }
    }

    @GetMapping("/{pageNo}/{pageSize}")
    public Result PageQuery(@PathVariable Integer pageNo, @PathVariable Integer pageSize){
        Page<Question> page=Page.of(pageNo,pageSize);
        page.addOrder(new OrderItem("question_id",true));
        iQuestionService.page(page);

        List<Question> questionList=page.getRecords();

        //创建包含额外信息的Map
        Map<String,Object> res=new HashMap<>();
        res.put("list",questionList);
        res.put("total",page.getTotal());
        return Result.success(res);
    }

    /*根据多项条件检索*/
    @GetMapping("/search")
    public Result selectByKeyword(@RequestParam(required = false) String keyword,@RequestParam Integer pageNo,@RequestParam Integer pageSize,
                                  @RequestParam(required = false) Integer typeId,@RequestParam(required = false) Integer categoryId){
        Map<String,Object> res= iQuestionService.selectByKeyword(keyword,typeId,categoryId,pageNo,pageSize);
        if(res==null){
            return Result.error("根据条件检索失败");
        }else {
            return Result.success(res);
        }
    }
}
