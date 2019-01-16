package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.OrderModel;
import com.model.ShippingRecordModel;

public interface OrderDao {
	OrderModel getOrderByOrderId(@Param("orderId")String orderId);
	
	List<ShippingRecordModel> getRecordsById(@Param("orderId")String orderId);

	List<OrderModel> getOrderByUserId(@Param("userId")String userId);
	
	Integer addOrder(OrderModel orderModel);
	
	Integer addRecord(ShippingRecordModel model);
	
	Integer testInsert();
}
