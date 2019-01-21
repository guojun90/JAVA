package com.entity;
/**
* @author 作者 Guo Jun
* @version 创建时间：2019年1月18日 下午9:51:26
* 类说明
*/
public class ClickButton extends AbstractButton{
	private String type = "click";
	private String key;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public ClickButton(String name, String key) {
		super(name);
		this.key = key;
	}
	
}
