package com.example.ch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.ch.model.Comments;
import com.example.ch.model.CommentsExample;
@Mapper
public interface CommentsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.comments
     *
     * @mbg.generated Fri Oct 18 22:59:48 JST 2024
     */
    long countByExample(CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.comments
     *
     * @mbg.generated Fri Oct 18 22:59:48 JST 2024
     */
    int deleteByExample(CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.comments
     *
     * @mbg.generated Fri Oct 18 22:59:48 JST 2024
     */
    int deleteByPrimaryKey(String commentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.comments
     *
     * @mbg.generated Fri Oct 18 22:59:48 JST 2024
     */
    int insert(Comments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.comments
     *
     * @mbg.generated Fri Oct 18 22:59:48 JST 2024
     */
    int insertSelective(Comments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.comments
     *
     * @mbg.generated Fri Oct 18 22:59:48 JST 2024
     */
    List<Comments> selectByExample(CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.comments
     *
     * @mbg.generated Fri Oct 18 22:59:48 JST 2024
     */
    Comments selectByPrimaryKey(String commentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.comments
     *
     * @mbg.generated Fri Oct 18 22:59:48 JST 2024
     */
    int updateByExampleSelective(@Param("record") Comments record, @Param("example") CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.comments
     *
     * @mbg.generated Fri Oct 18 22:59:48 JST 2024
     */
    int updateByExample(@Param("record") Comments record, @Param("example") CommentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.comments
     *
     * @mbg.generated Fri Oct 18 22:59:48 JST 2024
     */
    int updateByPrimaryKeySelective(Comments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.comments
     *
     * @mbg.generated Fri Oct 18 22:59:48 JST 2024
     */
    int updateByPrimaryKey(Comments record);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    List<String> selectAllCommentIds();
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    void updateByUserId(String newUserId, String userId);
}