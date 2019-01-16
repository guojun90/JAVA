package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserSessionUtil{
	public static String getUserName(HttpServletRequest request) {
		//得到session对象
        HttpSession session = request.getSession(false);
        if(session==null){
            return null;
        }
        //取出用户名
        return (String)session.getAttribute("loginName");
	}
	public static void logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
	}

}
