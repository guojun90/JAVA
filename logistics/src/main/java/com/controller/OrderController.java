package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entity.AccessToken;
import com.model.OrderModel;
import com.model.ShippingRecordModel;
import com.service.OrderService;
import com.service.impl.OrderServiceForWXImpl;
import com.util.JsonUtil;
import com.util.PraseRequestUtil;
import com.util.R;
import com.util.ResponseUtils;
import com.util.SignatureCheckUtil;
import com.util.UserSessionUtil;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderServiceForWXImpl orderServiceForWXImpl;
	
	static private Logger logger =Logger.getLogger(OrderController.class);

	/**
	 * 添加订单信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addOrderInfo(HttpServletRequest request, HttpServletResponse response) {
		R r = R.error("");
		String user = UserSessionUtil.getUserName(request);
		if(user==null || "".equals(user)) {
			r = R.error("请登录");
			ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
			return;
		}
		logger.info("[user:"+user+"] add order.");
		String orderId = getParameter(request, "orderId");
		String shippingOrderNo = getParameter(request, "shippingOrderNo");
		String cargoName = getParameter(request, "cargoName");
		String shippingDate = getParameter(request, "shippingDate");
		String senderName = getParameter(request, "senderName");
		String fromLocation = getParameter(request, "fromLocation");
		String toLocation = getParameter(request, "toLocation");
		String orderStatus = getParameter(request, "orderStatus");
		String orderReceptTime = getParameter(request, "orderReceptTime");
		String senderPhone = getParameter(request, "senderPhone");
		String receiverPhone = getParameter(request, "receiverPhone");
		String receiverName = getParameter(request, "receiverName");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderId", orderId);
		params.put("shippingOrderNo", shippingOrderNo);
		params.put("cargoName", cargoName);
		params.put("shippingDate", shippingDate);
		params.put("senderName", senderName);
		params.put("fromLocation", fromLocation);
		params.put("toLocation", toLocation);
//		params.put("orderStatus", orderStatus);
//		params.put("orderReceptTime", orderReceptTime);
		params.put("senderPhone", senderPhone);
		params.put("receiverPhone", receiverPhone);
		params.put("receiverName", receiverName);
		// 校验参数是否为空
		String checkResult = checkParams(params);
		if (!"success".equals(checkResult)) {
			r = R.error(checkResult);
			ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
			return;
		}
		OrderModel order = new OrderModel();
		order.setCargoName(cargoName);
		order.setFromLocation(fromLocation);
		order.setOrderId(orderId);
		order.setOrderStatus(orderStatus);
		order.setReceiverName(receiverName);
		order.setReceiverPhone(receiverPhone);
		order.setSenderName(senderName);
		order.setSenderPhone(senderPhone);
		order.setShippingDate(shippingDate);
		order.setToLocation(toLocation);
		order.setShippingOrderNo(shippingOrderNo);
		r = orderService.addOrder(order);
		ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
	}

	/**
	 * 添加订单路由信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addRecord", method = RequestMethod.POST)
	public void updateOrderRecord(HttpServletRequest request, HttpServletResponse response) {
		R r = R.error("");
		String user = UserSessionUtil.getUserName(request);
		if(user==null || "".equals(user)) {
			r = R.error("请登录");
			ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
			return;
		}
		logger.info("[user:"+user+"] add record.");
		String orderId = getParameter(request, "orderId");
		String updateTime = getParameter(request, "updateTime");
		String updateMsg = getParameter(request, "updateMsg");
		String updateStatus = getParameter(request, "updateStatus");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderId", orderId);
		params.put("updateTime", updateTime);
		params.put("updateMsg", updateMsg);
		params.put("updateStatus", updateStatus);
		// 校验参数是否为空
		String checkResult = checkParams(params);
		if (!"success".equals(checkResult)) {
			r = R.error(checkResult);
		}
		ShippingRecordModel model = new ShippingRecordModel();
		model.setOrderId(orderId);
		model.setUpdateMsg(updateMsg);
		model.setUpdateStatus(updateStatus);
		model.setUpdateTime(updateTime);
		r = orderService.updateOrderRecord(model);
		ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
	}

	/**
	 * 根据订单编号查询订单信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/query")
	public void queryOrder(HttpServletRequest request, HttpServletResponse response) {
		R r =R.error("");
		String user = UserSessionUtil.getUserName(request);
		if(user==null || "".equals(user)) {
			r = R.error("请登录");
			ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
			return;
		}
		String orderId = getParameter(request, "orderId");
		logger.info("[user:"+user+"] query order info by orderId.orderId="+orderId);
		r = orderService.queryOrder(orderId);
		ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
	}
	/**
	 * 根据订单编号查询订单信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/query/records")
	public void queryOrderRecords(HttpServletRequest request, HttpServletResponse response) {
		R r = R.error("");
		String user = UserSessionUtil.getUserName(request);
		if(user==null || "".equals(user)) {
			r = R.error("请登录");
			ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
			return;
		}
		String orderId = getParameter(request, "orderId");
		logger.info("[user:"+user+"] query record by orderId.orderId="+orderId);
		r = orderService.queryOrderRecords(orderId);
		ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
	}

	@RequestMapping(value = "/list/all")
	public void queryUserOrders(HttpServletRequest request, HttpServletResponse response) {
		String userId = getParameter(request, "userId");
		R r = orderService.listAllOrder(userId);
		ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
	}

	private String getParameter(HttpServletRequest request, String str) {
		return request.getParameter(str) == null ? "" : request.getParameter(str);
	}

	@RequestMapping(value = "/test")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		R r = orderService.test();
		ResponseUtils.renderJson(response, JsonUtil.convertObject2Json(r));
	}

	private String checkParams(HashMap<String, String> params) {
		Set keys = params.keySet();
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if ("".equals(params.get(key))) {
				return key + "不能为空";
			}
		}
		return "success";
	}
	
	@RequestMapping(value="/testWX",method = RequestMethod.GET)
	public void testWX(HttpServletRequest request, HttpServletResponse response) {
		String signature = getParameter(request, "signature");
		String timestamp = getParameter(request, "timestamp");
		String nonce = getParameter(request, "nonce");
		String echostr = getParameter(request, "echostr");
		logger.info("signature:"+signature);
		logger.info("timestamp:"+timestamp);
		logger.info("nonce:"+nonce);
		logger.info("echostr:"+echostr);
		
		if(SignatureCheckUtil.checkSignature(signature, timestamp, nonce)) {
			logger.info("pass check!");
			try {
				PrintWriter out = response.getWriter();
				out.write(echostr);
			} catch (IOException e) {
				logger.info("写消息异常"+e);
			}
			
		}
	}
	@RequestMapping(value="/testWX",method = RequestMethod.POST)
	public void responseReq(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map<String, String> requestMap = PraseRequestUtil.praseReqest(request.getInputStream());
		System.out.println(requestMap);
		
		String responseMsgContent = orderServiceForWXImpl.queryOrderRecords(requestMap.get("Content"));
		String xmlStr = ResponseUtils.getResponse(requestMap, responseMsgContent);
		logger.info("xmlStr:"+xmlStr);
		try {
			PrintWriter out = response.getWriter();
			out.println(xmlStr);
			out.close();
		} catch (IOException e) {
			logger.info("写消息异常"+e);
		}
	}
	
	@RequestMapping(value = "/test/token")
	public void testToken(HttpServletRequest request, HttpServletResponse response) {
		AccessToken token = orderServiceForWXImpl.geToken();
	}

}
