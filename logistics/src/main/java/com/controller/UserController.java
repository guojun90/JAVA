package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.UserService;
import com.util.JsonUtil;
import com.util.R;
import com.util.ResponseUtils;
import com.util.UserSessionUtil;


@RestController
@RequestMapping("/user")
public class UserController {
	
	Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/listAllUser")
	public void listAllUser() {
		logger.info("start list all user.");
		userService.listAllUser();
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public void  login(HttpServletRequest request, HttpServletResponse response) {
		R r ;
		String userName = getParameter(request.getParameter("userName"));
		String pwd = getParameter(request.getParameter("pwd"));
		boolean checkResult = userService.loginCheck(userName, pwd);

		if(checkResult) {
			r = R.ok("登录成功");
			//创建session对象
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            session.setAttribute("loginName", userName);
            logger.info(userName+"登录成功.");
		}else {
			r = R.error("登录失败");
		}
		ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
	}
	
	@RequestMapping(value = "/logout")
	public void louout(HttpServletRequest request, HttpServletResponse response) {
		String userName = UserSessionUtil.getUserName(request);
		UserSessionUtil.logout(request);
		R r = R.ok("");
		ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
		logger.info(userName+"登出成功.");
	}
	
	
	private String getParameter(String value) {
		return value==null? "":value;
	}
}
