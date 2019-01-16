package com.util;
/**
* @author 作者 Guo Jun
* @version 创建时间：2019年1月7日 下午9:52:16
* 类说明
*/

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class PraseRequestUtil {
	
	private static Logger logger = Logger.getLogger(PraseRequestUtil.class);
	
	public static Map<String, String> praseReqest(InputStream in){
		
		HashMap<String , String> resultMap = new HashMap<>();
		SAXReader reader = new SAXReader();
		
		try {
			Document document = reader.read(in);
			List<Element> elements = document.getRootElement().elements();
			for(Element e:elements) {
				resultMap.put(e.getName(), e.getStringValue());
			}
		} catch (DocumentException e) {
			logger.info("解析失败"+e);
		}
		return resultMap;
		
	}

}
