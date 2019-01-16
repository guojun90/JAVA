package com.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.OrderDao;
import com.model.OrderModel;
import com.model.ShippingRecordModel;
import com.service.OrderServiceForWX;

/**
* @author 作者 Guo Jun
* @version 创建时间：2019年1月16日 下午10:13:07
* 类说明
*/
public class OrderServiceForWXImpl implements OrderServiceForWX {
	
	@Autowired
	private OrderDao orderDao;
	
	private static Logger logger = Logger.getLogger(OrderServiceForWXImpl.class);

	@Override
	public String queryOrderRecords(String orderId) {
		// TODO Auto-generated method stub
		StringBuilder retVal = new StringBuilder();
		OrderModel order = null;
		List<ShippingRecordModel> records = null;
		try {
			logger.info("orderId="+orderId);
			// 查询订单信息
			order = orderDao.getOrderByOrderId(orderId);
			if (order != null) {
				// 订单信息存入R
				logger.debug("get order info success.order id:" + order.getOrderId());

				// 查询路由记录信息
				records = orderDao.getRecordsById(orderId);
				order.setRecords(records);

				// 准备返回数据
				retVal.append(order.toString());

			} else {
				retVal.append("抱歉，无您所查订单信息，请回复正确的订单编号");
			}

		} catch (Exception e) {
			logger.info("数据库处理错误查询"+e);
			retVal.append("抱歉，无您所查订单信息，请回复正确的订单编号");
		}
		
		logger.info("response msg:" + retVal.toString());
		return retVal.toString();
	}

}
