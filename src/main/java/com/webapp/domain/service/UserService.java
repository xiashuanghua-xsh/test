package com.webapp.domain.service;

import java.util.List;

import com.webapp.domain.pojo.User;

public interface UserService {
	int addUser(User user);
	
	int insert(User user);

    List<User> findAllUser(int pageNum, int pageSize);
}
