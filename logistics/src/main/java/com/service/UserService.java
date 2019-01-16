package com.service;

public interface UserService {

	void listAllUser();
	boolean loginCheck(String userName,String pwd);
}
