package com.webapp.domain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.webapp.domain.pojo.User;
@Mapper
public interface UserMapper {
	int deleteByPrimaryKey(Integer userId);

    int insert(User record);
    
    //@Insert("INSERT INTO T_USER(NAME, PASSWORD, PHONE) VALUES(#{name}, #{password}, #{phone})")
    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //这个方式我自己加的
    List<User> selectAllUser();
}
