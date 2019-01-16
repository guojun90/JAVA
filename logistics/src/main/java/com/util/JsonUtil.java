package com.util;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Json处理辅助类
 * @author CZ255009
 * @date 2018年5月15日
 */
public class JsonUtil {
	
	private static Logger logger = Logger.getLogger(JsonUtil.class);
	
	/**
	 * 将Object转化为json字符串
	 * @author CZ255009
	 * @date 2018年5月15日
	 * @param obj
	 * @return
	 */
	public static String convertObject2Json(Object obj) {
		//return JSON.toJSONString(obj);
		return new Gson().toJson(obj);
	}
	
	/**
	 * 将json字符串转化为Object
	 * @author CZ255009
	 * @date 2018年5月15日
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T>T convertJson2Object(String json, Class<T> clazz) {
		if(CommonUtil.isEmpty(json)) 
			return null;
		
		//return JSON.parseObject(json, clazz);
		return new Gson().fromJson(json, clazz);
	}
	
	/**
	 * 将json字符串<list类型>转化为List<Object>
	 * @author CZ255009
	 * @date 2018年5月15日
	 * @param json
	 * @param clazz
	 * @return
	 */
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List convertJson2List(String json, Class clazz) {
		if(CommonUtil.isEmpty(json)) 
			return null;
		
		return JSON.parseArray(json, clazz);
	}*/
	
	/**
	 * 获取jsonObject中的field的字符串类型，否则返回空
	 * @author CZ255009
	 * @date 2018年5月22日
	 * @param jsonObject
	 * @param field
	 * @return
	 */
	public static String getJsonString(JsonObject jsonObject, String field){
		try {
			if(jsonObject == null || jsonObject.get(field).isJsonNull())
				return null;
			return jsonObject.get(field).getAsString();
		} catch (Exception e) {
			logger.error(e);
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取jsonObject中的field的字符串类型，否则返回0
	 * @author CZ255009
	 * @date 2018年5月22日
	 * @param jsonObject
	 * @param field
	 * @return
	 */
	public static int getJsonInt(JsonObject jsonObject, String field){
		try {
			if(jsonObject == null || jsonObject.get(field).isJsonNull())
				return 0;
			return jsonObject.get(field).getAsInt();
		} catch (Exception e) {
			logger.error(e);
			//e.printStackTrace();
			return 0;
		}
	}
	
}
