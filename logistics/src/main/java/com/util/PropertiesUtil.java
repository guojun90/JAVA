package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class PropertiesUtil {
	
	private static final Logger logger = Logger.getLogger(PropertiesUtil.class);

	private static Properties properties = new Properties();
	
	private static PropertiesUtil instance = null;

	public static String PROP_PATH = "D:\\GitHub\\myProject\\logistics\\src\\main\\resources\\conf";
	private PropertiesUtil() {

		String filePath = PROP_PATH + File.separator;

		try {

			File conFile = new File(filePath + "wechat.properties");

			if (conFile.exists()) {
				properties.load(new FileInputStream(conFile));
			}

		} catch (IOException e) {
			logger.error("The Exception occured.", e);
		}
	}

	public synchronized static PropertiesUtil getInstance() {
		if (null == instance) {
			instance = new PropertiesUtil();
		}

		return instance;
	}

	/**
	 *	 获取参数值
	 * 
	 * @param key
	 *            properites的key值
	 * @param defValue
	 * 	默认值
	 * @return
	 */
	public static String getValues(String key) {
		String rtValue = null;

		if (null == key) {
			logger.error("key is null");
		} else {
			rtValue = PropertiesUtil.getInstance().getPropertiesValue(key);
		}

		return rtValue;
	}

	/**
	 * 根据key值获取server.properties的值
	 * 
	 * @param key
	 * @return
	 */
	private String getPropertiesValue(String key) {
		String rtValue = properties.getProperty(key);
		return rtValue;
	}
	public static void main(String[] args) {
//		String path = "D:\\GitHub\\myProject\\logistics\\src\\main\\resources\\conf";
//		PropertiesUtil.PROP_PATH = path;
		String auth_url = PropertiesUtil.getValues("auth.url");
		System.out.println(auth_url);
	}
}
