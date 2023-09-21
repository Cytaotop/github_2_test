package com.cytao.mapper;

import com.cytao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectlogin(@Param("username")String username,@Param("password")String password);

    int selectUserCheckName(@Param("username")String username);

    int upUserPwd(@Param("username")String username,@Param("password")String password,@Param("newpassword")String newpassword);
}