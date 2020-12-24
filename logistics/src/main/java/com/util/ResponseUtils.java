package com.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.entity.BaseMessage;
import com.entity.TextMessage;
import com.google.gson.stream.JsonWriter;
import com.service.impl.OrderServiceForWXImpl;
import com.thoughtworks.xstream.XStream;

public class ResponseUtils {

	/*
	 * 返回json串
	 */
	public static void renderJson(HttpServletResponse response, String text) {
		// System.out.print(text);
		render(response, "text/plain;charset=UTF-8", text);
	}

	/*
	 * 返回文本
	 */
	public static void renderText(HttpServletResponse response, String text) {
		render(response, "text/plain;charset=UTF-8", text);
	}

	/*
	 * 发送内容,使用UTF-8编码
	 */
	public static void render(HttpServletResponse response, String contentType, String text) {
		response.setContentType(contentType);
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
		}
	}

	/*
	 * 页面异步回调返回Json
	 */
	public static void outputJson(HttpServletResponse response, Object obj) {
		String s = JsonUtil.convertObject2Json(obj);// JsonWriter.toJson(obj, false);
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getResponse(Map<String, String> requestMap, OrderServiceForWXImpl orderServiceForWXImpl) {
		BaseMessage msg = null;
		String returnXmlStr = null;
		String msgType = requestMap.get("MsgType");
		switch (msgType) {
		case "text":
			msg = dealTextMsg(requestMap, orderServiceForWXImpl);
			break;
		case "image":

			break;
		case "voice":

			break;
		case "video":

			break;
		case "shortvideo":

			break;
		case "location":

			break;
		case "link":

			break;
		case "event":
			msg = dealEventMsg(requestMap, orderServiceForWXImpl);
			break;

		default:
			break;
		}

		if (msg != null) {
			// 把消息对象处理为xml数据包
			returnXmlStr = beanToXml(msg);
		}
		return returnXmlStr;
	}

	/**
	 * 处理文本消息
	 * 
	 * @param reqestMap
	 * @return
	 */
	private static BaseMessage dealTextMsg(Map<String, String> reqestMap, OrderServiceForWXImpl orderServiceForWXImpl) {
		String orderId = reqestMap.get("Content");
		String responseMsgContent = orderServiceForWXImpl.queryOrderRecords(orderId);
		return new TextMessage(reqestMap, responseMsgContent);
	}
	
	private static BaseMessage dealEventMsg(Map<String, String> requestMap, OrderServiceForWXImpl orderServiceForWXImpl) {
		String event = requestMap.get("Event");
		switch (event) {
		case "CLICK":
			return dealClickEvent(requestMap, orderServiceForWXImpl);
		case "VIEW":
			return dealViewEvent(requestMap);

		case "subscribe":
			orderServiceForWXImpl.saveSubscribeUserInfo(requestMap);
			return new TextMessage(requestMap, "您好,欢迎关注谊托物流！直接回复单号查询物流信息。");
		case "unsubscribe":
			orderServiceForWXImpl.unSubscribe(requestMap);
			return new TextMessage(requestMap, "");
		default:
			return new TextMessage(requestMap, "操作错误");
		}
	}

	private static BaseMessage dealViewEvent(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		return null;
	}

	private static BaseMessage dealClickEvent(Map<String, String> requestMap, OrderServiceForWXImpl orderServiceForWXImpl) {
		String key = requestMap.get("EventKey");
		switch (key) {
		case "cargoSearch":
			return new TextMessage(requestMap, "您好！直接回复订单编号即可查询物流信息");

		default:
			break;
		}
		return null;
	}

	private static String beanToXml(BaseMessage msg) {
		XStream stream = new XStream();

		stream.processAnnotations(TextMessage.class);
		String xmlStr = stream.toXML(msg);
		return xmlStr;
	}

	public static String str2xml(String str) {
		XStream stream = new XStream();
		return stream.toXML(str);
	}
}
