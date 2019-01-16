package com.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrderDao;
import com.model.OrderModel;
import com.model.ShippingRecordModel;
import com.service.OrderService;
import com.util.R;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public R queryOrderRecords(String orderId) {
		R r = null;
		List<ShippingRecordModel> records = null;
		try {
			logger.info("orderId="+orderId);
			// 查询路由记录信息
			records = orderDao.getRecordsById(orderId);
			r = new R();
			r.put(records);
		} catch (Exception e) {
			logger.info("数据库处理错误查询"+e);
			r = R.error("数据库查询出错");
		}
		logger.info("response msg:" + r);
		return r;
	}

	private static Logger logger = Logger.getLogger(OrderService.class);

	@Autowired
	private OrderDao orderDao;

	/**
	 * 查询订单的路由信息
	 */
	@Override
	public R queryOrder(String orderId) {
		R r = null;
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
				r = new R(order);

			} else {
				r = R.error("无此订单，订单编号:" + orderId);
			}

		} catch (Exception e) {
			logger.info("数据库处理错误查询"+e);
			r = R.error("数据库查询出错");
		}
		
		logger.info("response msg:" + r);
		return r;
	}

	/**
	 * 查询用户的订单信息
	 */
	@Override
	public R listAllOrder(String userId) {
		R r = null;
		try {
			logger.info("userId="+userId);
			//查询此用户的所有订单
			List<OrderModel> orders = orderDao.getOrderByUserId(userId);
			if (orders != null && orders.size() != 0) {
				r = new R(orders);
			}else{
				r=R.error("无此用户订单");
			}

		} catch (Exception e) {
			r = R.error("查询数据库出错"+e);
		}
		logger.info("response msg:" + r);
		return r;
	}

	@Override
	public R test() {
		R r = new R();
		int i = orderDao.testInsert();
		r.put("insert", i);
		return r;
	}

	@Override
	public R addOrder(OrderModel order) {
		try {
			OrderModel orderModel = orderDao.getOrderByOrderId(order.getOrderId());
			if(orderModel!=null) {
				logger.info("订单编号已存在");
				return R.error("订单编号已存在");
			}
			orderDao.addOrder(order);
		} catch (Exception e) {
			logger.info("插入失败订单信息失败"+e);
			return R.error("插入失败订单信息失败");
		}
		return R.ok("插入订单信息成功.");
	}

	@Override
	public R updateOrderRecord(ShippingRecordModel model) {
		try {
			logger.info("[orderId="+model.getOrderId()+",updateMsg="+model.getUpdateMsg()+",updateTime="+model.getUpdateTime()+",updateStatus="+model.getUpdateStatus()+"]");
			orderDao.addRecord(model);
		} catch (Exception e) {
			logger.info("更新订单路由信息失败"+e);
			return R.error("更新订单路由信息失败");
		}
		return R.ok("更新路由信息成功.");
	}

}
