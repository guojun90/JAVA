package com.util;

import java.util.HashMap;
import java.util.List;

import com.model.OrderModel;
import com.model.ShippingRecordModel;


public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R() {
		put("responseCode", 0);
		put("responseMessage", "success");
		put("responseData", 0);
		
	}
	public static R error(String msg) {
		R r = new R();
		r.put("responseCode", -1);
		r.put("responseMessage", "failed");
		r.put("responseData", msg);
		return r;
	}
	public static R ok(String msg) {
		R r = new R();
		r.put("responseCode", 0);
		r.put("responseMessage", "success");
		r.put("responseData", msg);
		return r;
	}
	public R(List<OrderModel> orders) {
		put("responseCode", 0);
		put("responseMessage", "success");
		put("responseData", orders);
	}
	public R(OrderModel order) {
		put("responseCode", 0);
		put("responseMessage", "success");
		HashMap<String, Object> params = new HashMap<>();
		params.put("cargoName", setParam(order.getCargoName()));
		params.put("cargoName", setParam(order.getCargoName()));
		params.put("orderId", setParam(order.getOrderId()));
		params.put("orderReceptTime", setParam(order.getOrderReceptTime()));
		params.put("orderStatus", setParam(order.getOrderStatus()));
		params.put("receiverName", setParam(order.getReceiverName()));
		params.put("receiverPhone", setParam(order.getReceiverPhone()));
		params.put("senderName", setParam(order.getSenderName()));
		params.put("senderPhone", setParam(order.getSenderPhone()));
		params.put("shippingDate", setParam(order.getShippingDate()));
		params.put("shippingOrderNo", setParam(order.getShippingOrderNo()));
		params.put("toLocation", setParam(order.getToLocation()));
		params.put("fromLocation",setParam(order.getFromLocation()));
		params.put("redords", order.getRecords()==null?"":order.getRecords().toArray());
		put("responseData", params);
		
	}
	private String setParam(String str) {
		return str==null?"":str;
	}

	public final R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	public final R put(List<ShippingRecordModel> records) {
		super.put("responseData", records);
		return this;
	}
	
}
