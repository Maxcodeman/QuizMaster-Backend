package com.ncu.intell_learning_sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("question")
@ApiModel(value="Question对象", description="")
public class Question{

    @ApiModelProperty(value = "题目编号")
    @TableId(value = "question_id", type = IdType.AUTO)
    private Long questionId;

    @ApiModelProperty(value = "题型")
    @TableField("question_type")
    private Integer questionType;

    @ApiModelProperty(value = "题目分类")
    @TableField("question_category")
    private Integer questionCategory;

    @ApiModelProperty(value = "题目内容")
    @TableField("question_content")
    private String questionContent;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "收藏次数(总)")
    @TableField("star_count")
    private Long starCount;

    @ApiModelProperty(value = "错误次数(总)")
    @TableField("wrong_count")
    private Long wrongCount;

    @ApiModelProperty(value = "题目答案")
    @TableField("question_answer")
    private String questionAnswer;


}
