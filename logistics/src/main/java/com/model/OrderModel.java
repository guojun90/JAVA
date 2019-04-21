package com.model;

import java.util.Date;
import java.util.List;

public class OrderModel {
	/*
	 * 订单编号
	 */
	private String orderId;

	/*
	 * 托运单号
	 */
	private String shippingOrderNo;

	/*
	 * 货物名称
	 */
	private String cargoName;

	/*
	 * 托运日期
	 */
	private String shippingDate;

	/*
	 * 发货人
	 */
	private String senderName;

	/*
	 * 收货人
	 */
	private String receiverName;

	/*
	 * 发货地点
	 */
	private String fromLocation;

	/*
	 * 目的地址
	 */
	private String toLocation;

	/*
	 * 订单状态
	 */
	private String orderStatus;

	/*
	 * 订单签收时间
	 */
	private String orderReceptTime;

	/*
	 * 寄件人号码
	 */
	private String senderPhone;

	/*
	 * 收件人号码
	 */
	private String receiverPhone;
	
	private List<ShippingRecordModel> records;

	public List<ShippingRecordModel> getRecords() {
		return records;
	}

	public void setRecords(List<ShippingRecordModel> records) {
		this.records = records;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getShippingOrderNo() {
		return shippingOrderNo;
	}

	public void setShippingOrderNo(String shippingOrderNo) {
		this.shippingOrderNo = shippingOrderNo;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getOrderReceptTime() {
		return orderReceptTime;
	}

	public void setOrderReceptTime(String orderReceptTime) {
		this.orderReceptTime = orderReceptTime;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	
	@Override
	public String toString() {
//		String recordsStr = getRecordsStr(records);
		return "单号：("+orderId+")\r\n"
				+ "货物名称："+cargoName+"\r\n"
				+ "托运日期："+shippingDate+"\r\n"
				+ "发货人："+senderName+"\r\n"
				+ "发货人电话："+senderPhone+"\r\n"
				+ "收货人："+receiverName+"\r\n"
				+ "收货人电话："+receiverPhone+"\r\n"
				+ "货物运输跟踪记录：\r\n"
				+ getRecordsStr(records);
	}
	
	private String getRecordsStr(List<ShippingRecordModel> recordList) {
		StringBuilder strBuilder = new StringBuilder();
		for(ShippingRecordModel model:recordList) {
			strBuilder.append(model.toString());
		}
		return strBuilder.toString();
	}

}
