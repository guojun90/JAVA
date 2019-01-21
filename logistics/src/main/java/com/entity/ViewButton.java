package com.entity;
/**
* @author 作者 Guo Jun
* @version 创建时间：2019年1月18日 下午9:54:59
* 类说明
*/
public class ViewButton extends AbstractButton{
	
	private String type = "view";
	private String url;

	public String getType() {
		return type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ViewButton(String name, String url) {
		super(name);
		this.url = url;
	}
	
	

}
