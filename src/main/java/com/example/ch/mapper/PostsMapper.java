package com.example.ch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.ch.model.Posts;
import com.example.ch.model.PostsExample;
@Mapper
public interface PostsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    long countByExample(PostsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    int deleteByExample(PostsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    int deleteByPrimaryKey(String postId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    int insert(Posts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    int insertSelective(Posts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    List<Posts> selectByExample(PostsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    Posts selectByPrimaryKey(String postId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    int updateByExampleSelective(@Param("record") Posts record, @Param("example") PostsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    int updateByExample(@Param("record") Posts record, @Param("example") PostsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    int updateByPrimaryKeySelective(Posts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    int updateByPrimaryKey(Posts record);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    List<String> selectAllPostIds();
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    List<Posts> selectRecentPosts();
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    List<Posts> selectAllPost();
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.posts
     *
     * @mbg.generated Wed Jun 12 22:34:43 JST 2024
     */
    void updateByUserId(String newUserId, String userId);
    
}