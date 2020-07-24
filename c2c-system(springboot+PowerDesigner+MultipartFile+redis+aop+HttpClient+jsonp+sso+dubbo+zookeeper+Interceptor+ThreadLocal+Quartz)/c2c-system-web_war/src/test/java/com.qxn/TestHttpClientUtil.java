package com.qxn;

import com.qxn.util.HttpClientService;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

//需要用spring容器的时候加
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestHttpClientUtil {
	@Autowired
	private HttpClientService httpClientService;
	/**
	 */
	@Test
	public void testHttpClientUtil(){
		String url = "http://www.baidu.com";
		HashMap<String, String> params = new HashMap<>();
		params.put("id","1");
		params.put("name","tom");
		String result = httpClientService.doGet(url, params);
		System.out.println(result);
	}
}
