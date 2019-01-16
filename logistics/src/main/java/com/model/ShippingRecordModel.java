package com.model;

import java.util.Date;

public class ShippingRecordModel {
	/*
	 * 订单编号
	 */
	private String orderId;
	
	/*
	 * 更新时间
	 */
	private String updateTime;
	
	/*
	 * 更新信息
	 */
	private String updateMsg;
	
	/*
	 * 更新状态
	 */
	private String updateStatus;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateMsg() {
		return updateMsg;
	}

	public void setUpdateMsg(String updateMsg) {
		this.updateMsg = updateMsg;
	}

	public String getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
	@Override
	public String toString() {
		return "["+updateTime+"]\t"+updateMsg+"\r\n";
	}
	
}
