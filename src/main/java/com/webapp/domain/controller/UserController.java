package com.webapp.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.domain.pojo.User;
import com.webapp.domain.service.UserService;

@RestController
public class UserController {
	
	 @RequestMapping(value = "user/test.do",method= {RequestMethod.GET,RequestMethod.POST},produces = "text/plain;charset=UTF-8")
	 String index(){
	    return "测试Spring Boot";
	 }
	 

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "user/add.do", produces = { "application/json;charset=UTF-8" })
	public int addUser(User user) {
		return userService.insert(user);
	}

	@RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = { "application/json;charset=UTF-8" })
	public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {

		return userService.findAllUser(pageNum, pageSize);
	}
}
