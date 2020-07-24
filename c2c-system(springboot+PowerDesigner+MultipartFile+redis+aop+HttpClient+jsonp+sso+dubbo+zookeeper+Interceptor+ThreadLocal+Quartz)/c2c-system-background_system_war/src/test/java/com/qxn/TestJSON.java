package com.qxn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qxn.pojo.ProductDesc;

public class TestJSON {
	private static final ObjectMapper mapper = new ObjectMapper();
	/**
	 * 1.对象转化为json
	 */
	@Test
	public void toJSON() throws Exception {
		ProductDesc ProductDesc = new ProductDesc();
		ProductDesc.setItemId(1l)
				.setItemDesc("商品详情")
				.setCreated(new Date())
				.setUpdated(ProductDesc.getCreated());
		String json = mapper.writeValueAsString(ProductDesc);
		System.out.println(json);
		//将json转为对象
		ProductDesc desc = mapper.readValue(json, ProductDesc.class);
		System.out.println(desc);
	}
	@Test
	public void testList() throws Exception {
		ProductDesc ProductDesc1 = new ProductDesc();
		ProductDesc1.setItemId(1L)
				.setItemDesc("商品详情")
				.setCreated(new Date())
				.setUpdated(ProductDesc1.getCreated());
		ProductDesc ProductDesc2 = new ProductDesc();
		ProductDesc2.setItemId(1L)
				.setItemDesc("商品详情")
				.setCreated(new Date())
				.setUpdated(ProductDesc2.getCreated());
		List list = new ArrayList();
		list.add(ProductDesc1);
		list.add(ProductDesc2);
		String json = mapper.writeValueAsString(list);
		System.out.println(json);
//		List<ProductDesc> list2 = mapper.readValue(json, List.class);
		List<ProductDesc> list2 = mapper.readValue(json, list.getClass());
		System.out.println(list2);
	}
}
