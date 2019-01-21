package com.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
* @author 作者 Guo Jun
* @version 创建时间：2019年1月21日 下午9:47:07
* 类说明
*/
public class SubscribeUserInfoModel {
	@SerializedName("subscribe")
	private int subscribe;
	@SerializedName("openid")
	private String openId;
	@SerializedName("nickname")
	private String nickName;
	@SerializedName("sex")
	private int sex;
	@SerializedName("language")
	private String langeuage;
	@SerializedName("city")
	private String city;
	@SerializedName("province")
	private String province;
	@SerializedName("country")
	private String country;
	@SerializedName("headimgurl")
	private String headImgUrl;
	@SerializedName("subscribe_time")
	private String subscribeTime;
	@SerializedName("unionid")
	private String unionid;
	@SerializedName("remark")
	private String remark;
	@SerializedName("groupid")
	private String groupId;
	@SerializedName("tagid_list")
	private List<Integer> tagidList;
	@SerializedName("subscribe_scene")
	private String subscribeScene;
	@SerializedName("qr_scene")
	private Integer qrScene;
	@SerializedName("qr_scene_str")
	private String qrSceneStr;
	
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getLangeuage() {
		return langeuage;
	}
	public void setLangeuage(String langeuage) {
		this.langeuage = langeuage;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getTagidList() {
		return tagidList.toString();
	}
	public void setTagidList(List<Integer> tagidList) {
		this.tagidList = tagidList;
	}
	public String getSubscribeScene() {
		return subscribeScene;
	}
	public void setSubscribeScene(String subscribeScene) {
		this.subscribeScene = subscribeScene;
	}
	public Integer getQrScene() {
		return qrScene;
	}
	public void setQrScene(Integer qrScene) {
		this.qrScene = qrScene;
	}
	public String getQrSceneStr() {
		return qrSceneStr;
	}
	public void setQrSceneStr(String qrSceneStr) {
		this.qrSceneStr = qrSceneStr;
	}
	

}
