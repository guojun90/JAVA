package com.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtils {

	static Logger logger = Logger.getLogger(HttpUtils.class);

	static PoolingHttpClientConnectionManager cm = null;

	static final String FORM_MIME = "application/x-www-form-urlencoded";
	static final String JSON_MIME = "application/json";

	static {

		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(500);
		cm.setDefaultMaxPerRoute(25);

	}

	public static CloseableHttpClient getHttpClient() {
//		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		return httpClient;
	}

	public static String httpGet(String url, String contentType) {
		CloseableHttpClient httpClient = getHttpClient();

		HttpRequest httpGet = new HttpGet(url);
		httpGet.setHeader("Content-type", FORM_MIME);

		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute((HttpGet) httpGet);
			HttpEntity entity = response.getEntity();

			return EntityUtils.toString(entity, "UTF-8");

		} catch (Exception e) {
			logger.info("[HttpUtils]: execute exception. " + e);

		} finally {
			try {
				response.close();
				httpClient.close();
			} catch (IOException e) {
				logger.info("[HttpUtils]: close response exception. " + e);
			}
		}
		return null;
	}

	public static String post(String postURL, Map<String, String> paramMap) {
		String result = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = getHttpClient();
		// 创建httppost
		HttpPost httppost = new HttpPost(postURL);

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000)
				.setSocketTimeout(10000).build();

		httppost.setConfig(requestConfig);

		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (null != paramMap && !paramMap.isEmpty()) {
			for (Entry<String, String> param : paramMap.entrySet()) {
				formparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
			}
		}
		UrlEncodedFormEntity uefEntity = null;
		CloseableHttpResponse response = null;
		try {
			httppost.setHeader("Content-type", FORM_MIME);

			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			response = httpclient.execute(httppost);

			logger.info("[HttpUtils]executing request " + httppost.getURI());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			logger.error("[HttpUtils] e:" + e);
		} finally {
			try {
				response.close();
				httpclient.close();

			} catch (IOException e) {
				logger.error("[HttpUtils]executing error " + e);
			}
		}
		logger.info("[HttpUtils]executing result " + result);
		return result;
	}

	public static String postJson(String postURL, String jsonStr) {
		String result = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = getHttpClient();
		// 创建httppost
		HttpPost httppost = new HttpPost(postURL);

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000)
				.setSocketTimeout(40000).build();

		httppost.setConfig(requestConfig);

		// 创建参数队列
		StringEntity requestEntity = new StringEntity(jsonStr, "utf-8");
		httppost.setEntity(requestEntity);
		httppost.setHeader("Content-type", JSON_MIME);

		CloseableHttpResponse response = null;
		try {

			response = httpclient.execute(httppost);

			logger.info("[HttpUtils]executing request " + httppost.getURI());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			logger.error("[HttpUtils] e:" + e);
			e.printStackTrace();

		} finally {
			try {
				response.close();
				httpclient.close();

			} catch (IOException e) {
				logger.error("[HttpUtils]executing error " + e);
			}
		}
		logger.info("[HttpUtils]executing result " + result);
		return result;
	}

	public static void main(String[] args) {

		Map<String, String> params = new HashMap<String, String>();

		params.put("value", "15802155807#123,234");

		String re = HttpUtils.post("http://localhost:8080/bid-web/consumer/receive", params);

		System.out.println(re);
	}

}
