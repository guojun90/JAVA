package com.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.OrderDao;
import com.entity.AccessToken;
import com.entity.Button;
import com.entity.ClickButton;
import com.entity.SubButton;
import com.entity.ViewButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.model.OrderModel;
import com.model.ShippingRecordModel;
import com.service.OrderServiceForWX;
import com.util.HttpUtils;
import com.util.JsonUtil;

/**
 * @author 作者 Guo Jun
 * @version 创建时间：2019年1月16日 下午10:13:07 类说明
 */
public class OrderServiceForWXImpl implements OrderServiceForWX {

	@Autowired
	private OrderDao orderDao;

	private static Logger logger = Logger.getLogger(OrderServiceForWXImpl.class);

	private AccessToken token;
	private final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private final String APPID = "wx3f621fe647638b65";
	private final String APPSECRET = "a18b782008d0382f7b3007cf8673ecda";

	@Override
	public String queryOrderRecords(String orderId) {
		// TODO Auto-generated method stub
		StringBuilder retVal = new StringBuilder();
		OrderModel order = null;
		List<ShippingRecordModel> records = null;
		try {
			logger.info("orderId=" + orderId);
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
			logger.info("数据库处理错误查询" + e);
			retVal.append("抱歉，无您所查订单信息，请回复正确的订单编号");
		}

		logger.info("response msg:" + retVal.toString());
		return retVal.toString();
	}

	@Override
	public AccessToken geToken() {
		if (token == null || token.isExpired()) {
			getToken();
		}
		return token;
	}

	private void getToken() {
		String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		String response = HttpUtils.httpGet(url, "");
		JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
		String tokenStr = JsonUtil.getJsonString(jsonObject, "access_token");
		String expireIn = JsonUtil.getJsonString(jsonObject, "expires_in");
		token = new AccessToken(tokenStr, expireIn);
		logger.info("get token success!");
	}

	public static void main(String[] args) {

		AccessToken token = new OrderServiceForWXImpl().geToken();
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
		Button btn = new Button();
//		btn.getButton().add(new ClickButton("一级按钮", "btn1"));
//		btn.getButton().add(new ViewButton("一级跳转", "http://www.baidu.com"));
		// 第一个一级菜单及对应的二级菜单
		SubButton sbtn = new SubButton("查询");
		sbtn.getSub_button().add(new ClickButton("货物查询", "cargoSearch"));
		sbtn.getSub_button().add(new ViewButton("回单查询", "http://www.baidu.com"));
		sbtn.getSub_button().add(new ClickButton("回单上传", "uploadOrder"));
		sbtn.getSub_button().add(new ClickButton("我要发货", "sendCargo"));
		sbtn.getSub_button().add(new ClickButton("我的订单", "myOrder"));
		btn.getButton().add(sbtn);

		// 第二个一级菜单及对应的二级菜单
		SubButton sbtn2 = new SubButton("业务范围");
		sbtn2.getSub_button().add(new ClickButton("人工客服", "serverMan"));
		sbtn2.getSub_button().add(new ViewButton("上海到长沙", "http://www.baidu.com"));
		sbtn2.getSub_button().add(new ViewButton("上海到湖南", "http://www.baidu.com"));
		sbtn2.getSub_button().add(new ViewButton("上海到池州", "http://www.baidu.com"));
		sbtn2.getSub_button().add(new ViewButton("上海到北京", "http://www.baidu.com"));
		btn.getButton().add(sbtn2);

		// 第二个一级菜单及对应的二级菜单
		SubButton sbtn3 = new SubButton("关于我们");
		sbtn3.getSub_button().add(new ViewButton("公司简介", "http://www.baidu.com"));
		sbtn3.getSub_button().add(new ViewButton("企业文化", "http://www.baidu.com"));
		sbtn3.getSub_button().add(new ViewButton("实力展示", "http://www.baidu.com"));
		sbtn3.getSub_button().add(new ViewButton("部分客户", "http://www.baidu.com"));
		sbtn3.getSub_button().add(new ViewButton("联系我们", "http://www.baidu.com"));
		btn.getButton().add(sbtn3);

		String json = JsonUtil.convertObject2Json(btn);
		System.out.println(json);

		String response = HttpUtils.postJson(url + token.getToken(), json);
		System.out.println(response);
	}

}
