package com.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.model.AdminUserModel;
import com.service.UserService;
import com.util.SpringUtil;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	public void listAllUser() {
		SqlSessionFactory factory = (SqlSessionFactory) SpringUtil.getBean("sqlSessionFactory");
		logger.info(factory.toString());
		List<AdminUserModel> adminUsers = userDao.getAllUser();
		for(AdminUserModel aUser :adminUsers) {
			logger.info("name:"+aUser.getName()+"\tpwd:"+aUser.getPwd());
		}
	}
	
	/**
	 * 校验用户名、密码是否正确
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public boolean loginCheck(String userName,String pwd) {
		try {
			String dbPwd = userDao.getPwdByName(userName);
			return pwd.equals(dbPwd)? true:false;
		} catch (Exception e) {
			logger.info("查询数据库出错", e);
			return false;
		}
	}
}
