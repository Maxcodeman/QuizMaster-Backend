package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.FavoriteQuestion;
import com.ncu.quiz_master_backend.entity.Question;
import com.ncu.quiz_master_backend.entity.QuestionForUsers;
import com.ncu.quiz_master_backend.entity.WrongQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface QuestionForUsersMapper {

    @Select("""
            select a.question_id, a.question_desc, a.option_a, a.option_b, a.option_c, a.option_d, a.answer, a.category_id,coalesce(b.wrong_count,0) as wrong_count, a.is_favorite,a.type
            from (select q.question_id, q.question_desc, q.option_a, q.option_b, q.option_c, q.option_d, q.answer, q.category_id,coalesce(f.is_favorite,0)as is_favorite,q.type
                  from (select * from tb_question where category_id=#{categoryId}) as q left join tb_favorite as f
                        on q.category_id=#{categoryId} and f.user_id=#{userId} and f.question_id=q.question_id) as a left join tb_wrong as b
            on a.question_id=b.question_id and b.user_id=#{userId}""")
    List<QuestionForUsers> selectAllByCategoryId(Integer categoryId,Integer userId);


    @Select("select count(1) from tb_favorite where question_id=#{questionId} and user_id=#{userId}")
    Integer checkFavoriteExist(Integer questionId, Integer userId);
    @Update("update tb_favorite set is_favorite=#{isFavorite} where question_id=#{questionId} and user_id=#{userId}")
    void updateFavoriteState(FavoriteQuestion favoriteQuestion);
    @Insert("insert into tb_favorite (user_id, question_id, is_favorite) " +
            "values (#{userId},#{questionId},#{isFavorite})")
    void insertFavorite(FavoriteQuestion favoriteQuestion);


    @Select("select count(1) from tb_wrong where question_id=#{questionId} and user_id=#{userId}")
    Integer checkWrongExist(Integer questionId, Integer userId);
    @Update("update tb_wrong set wrong_count=wrong_count+1 where question_id=#{questionId} and user_id=#{userId}")
    void updateWrongCount(WrongQuestion wrongQuestion);
    @Insert("insert into tb_wrong (user_id, question_id, wrong_count) " +
            "values (#{userId},#{questionId},#{wrongCount})")
    void insertWrong(WrongQuestion wrongQuestion);

    @Select("""
                select f.user_id,f.question_id,q.question_desc,q.option_a,q.option_b,q.option_c,q.option_d,q.answer,q.category_id,
                      coalesce(w.wrong_count,0)as wrong_count,f.is_favorite ,q.type from tb_favorite as f
                left join  tb_wrong as w on  f.user_id=w.user_id and f.question_id=w.question_id
                left join tb_question as q on f.question_id=q.question_id where f.user_id=1 and f.is_favorite=1""")
    List<QuestionForUsers> getFavourByUserId(Integer userId);

    @Select("""
               select w.user_id,w.question_id,q.question_desc,q.option_a,q.option_b,q.option_c,q.option_d,q.answer,q.category_id,
                      w.wrong_count,coalesce(f.is_favorite,0)as is_favorite ,q.type from tb_wrong as w
                   left join  tb_favorite as f on w.user_id=f.user_id and w.question_id=f.question_id
               left join tb_question as q on w.question_id=q.question_id where w.user_id=1""")
    List<QuestionForUsers> getWrongByUserId(Integer userId);
}
