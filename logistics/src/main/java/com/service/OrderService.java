package com.service;

import com.model.OrderModel;
import com.model.ShippingRecordModel;
import com.util.R;

public interface OrderService {
	/**
	 * 	根据订单编号查询订单信息
	 * @param orderId
	 * @return
	 */
	R queryOrder(String orderId);
	
	/**
	 * 查询订单路由信息
	 * @param orderId
	 * @return
	 */
	R queryOrderRecords(String orderId);
	
	/**
	 * 根据用户编号查询用户所有订单
	 * @param userId
	 * @return
	 */
	R listAllOrder(String userId);
	
	/**
	 * 添加一个订单
	 * @param order
	 * @return
	 */
	R addOrder(OrderModel order);
	
	/**
	 * 更新订单信息
	 * @param model
	 * @return
	 */
	R updateOrderRecord(ShippingRecordModel model);
	
	R test();

}
