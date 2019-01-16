package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.AdminUserModel;

public interface UserDao {
	
	List<AdminUserModel> getAllUser();
	
	String getPwdByName(@Param("name") String name);


}
