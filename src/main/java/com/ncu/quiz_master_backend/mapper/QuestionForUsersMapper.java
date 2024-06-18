package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.FavoriteQuestion;
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

    @Select("select tb_favorite.question_id,\n" +
            "       tb_question.question_desc,\n" +
            "       tb_question.option_a,\n" +
            "       tb_question.option_b,\n" +
            "       tb_question.option_c,\n" +
            "       tb_question.option_d,\n" +
            "       tb_question.answer,\n" +
            "       tb_question.category_id,\n" +
            "       COALESCE(tb_wrong.wrong_count, 0) AS wrong_count,\n" +
            "       tb_favorite.is_favorite,\n" +
            "       tb_question.type\n" +
            "from tb_user\n" +
            "    Join tb_favorite on tb_user.user_id = tb_favorite.user_id\n" +
            "    join tb_question on tb_favorite.question_id = tb_question.question_id\n" +
            "    join tb_wrong on tb_question.question_id = tb_wrong.question_id\n" +
            "         AND tb_favorite.question_id = tb_wrong.question_id\n" +
            "where tb_favorite.user_id = #{userId} and tb_favorite.is_favorite = 1;")
    List<QuestionForUsers> getFavourByUserId(Integer userId);

    @Select("select tw.question_id,\n" +
            "tq.question_desc,\n" +
            "tq.option_a,\n" +
            "tq.option_b,\n" +
            "tq.option_c,\n" +
            "tq.option_d,\n" +
            "tq.answer,\n" +
            "tq.category_id,\n" +
            "COALESCE(tw.wrong_count, 0) AS wrong_count,\n" +
            "COALESCE(tf.is_favorite, 0) as is_favorite,\n" +
            "tq.type\n" +
            "from tb_wrong tw\n" +
            "join tb_question tq on tw.question_id = tq.question_id\n" +
            "left join tb_favorite tf on tq.question_id = tf.question_id\n" +
            "where tw.user_id = #{userId}")
    List<QuestionForUsers> getWrongByUserId(Integer userId);
}
