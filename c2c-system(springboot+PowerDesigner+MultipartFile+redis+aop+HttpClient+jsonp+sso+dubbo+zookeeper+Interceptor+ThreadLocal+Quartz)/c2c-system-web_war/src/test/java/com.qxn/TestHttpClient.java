package com.qxn;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	/**
	 * 1.实例化工具api对象
	 * 2.确定请求url地址
	 * 3.定义请求的方式get/post
	 * 4.发起http请求，并且获取响应的数据
	 * 5.判断状态码是否为200
	 * 6.获取服务器的返回值数据
	 * @throws Throwable 
	 * @throws ClientProtocolException 
	 */
	@Test
	public void testGet() throws ClientProtocolException, Throwable {
		CloseableHttpClient client = HttpClients.createDefault();
		String url = "https://www.baidu.com/";
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = client.execute(get);
		if(response.getStatusLine().getStatusCode() == 200) {
			String data = EntityUtils.toString(response.getEntity(),"utf-8");
			System.out.println(data);//html代码
		}
	}
}
