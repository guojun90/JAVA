package com.entity;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
* @author 作者 Guo Jun
* @version 创建时间：2019年1月7日 下午10:23:24
* 类说明
*/
@XStreamAlias("xml")
public class BaseMessage {
	@XStreamAlias("ToUserName")
	private String toUserName;
	@XStreamAlias("FromUserName")
	private String fromUserName;
	@XStreamAlias("CreateTime")
	private String createTime;
	@XStreamAlias("MsgType")
	private String msgType;
	@XStreamAlias("MsgId")
	private String msgId;
	
	
	public BaseMessage(Map<String, String> requestMap) {
		
		this.toUserName = requestMap.get("FromUserName");
		this.fromUserName = requestMap.get("ToUserName");
		this.msgId = requestMap.get("MsgId");
		this.createTime = System.currentTimeMillis()/1000+"";
	}
	@Override
	public String toString() {
		return "BaseMessage [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", msgId=" + msgId + "]";
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	

}
