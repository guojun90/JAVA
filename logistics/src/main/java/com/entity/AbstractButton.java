package com.entity;
/**
* @author 作者 Guo Jun
* @version 创建时间：2019年1月18日 下午9:48:04
* 类说明
*/
public abstract class AbstractButton {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AbstractButton(String name) {
		super();
		this.name = name;
	}
	
}
