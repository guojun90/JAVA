package com.service;
/**
* @author 作者 Guo Jun
* @version 创建时间：2019年1月16日 下午10:12:36
* 类说明
*/

import com.entity.AccessToken;

public interface OrderServiceForWX {
	public String queryOrderRecords(String orderId);
	
	public AccessToken geToken();
	
	void setButton();

}
