package com.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author 作者 Guo Jun
 * @version 创建时间：2019年1月17日 上午12:31:28 类说明
 *          https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&
 */
public class AccessToken {
	@SerializedName("access_token")
	private String token;
	@SerializedName("expires_in")
	private long expireTime;

	public AccessToken(String token, String expireIn) {
		this.token = token;
		this.expireTime = System.currentTimeMillis() + Integer.parseInt(expireIn) * 1000;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireIn) {
		this.expireTime = System.currentTimeMillis() + Integer.parseInt(expireIn) * 1000;
	}

	/**
	 * 判断当前时间token是否过期
	 * 
	 * @return
	 */
	public boolean isExpired() {
		return System.currentTimeMillis() > this.expireTime;
	}
}
