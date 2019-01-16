package com.entity;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
* @author 作者 Guo Jun
* @version 创建时间：2019年1月7日 下午10:30:30
* 类说明
*/
@XStreamAlias("xml")
public class TextMessage extends BaseMessage{
	@XStreamAlias("Content")
	private String content;

	public TextMessage(Map<String, String> requestMap, String content) {
		super(requestMap);
		this.setMsgType("text");
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "TestMessage [content=" + content + ", toString()=" + super.toString() + "]";
	}

}
