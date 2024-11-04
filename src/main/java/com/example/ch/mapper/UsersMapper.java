package com.example.ch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.ch.model.Users;
import com.example.ch.model.UsersExample;
@Mapper
public interface UsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Tue Jun 04 01:23:00 JST 2024
     */
    long countByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Tue Jun 04 01:23:00 JST 2024
     */
    int deleteByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Tue Jun 04 01:23:00 JST 2024
     */
    int insert(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Tue Jun 04 01:23:00 JST 2024
     */
    int insertSelective(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Tue Jun 04 01:23:00 JST 2024
     */
    List<Users> selectListByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Tue Jun 04 01:23:00 JST 2024
     */
    Users selectByExample(UsersExample example);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Tue Jun 04 01:23:00 JST 2024
     */
    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Tue Jun 04 01:23:00 JST 2024
     */
    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Tue Jun 04 01:23:00 JST 2024
     */
    List<Users> selectUsersNameList(List<String> userIdList);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
     *
     * @mbg.generated Tue Jun 04 01:23:00 JST 2024
     */
    List<Users> selectAll();
    
}