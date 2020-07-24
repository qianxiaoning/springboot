package com.qxn.dubbo.service;

import java.util.List;

import com.qxn.pojo.Cart;

public interface DubboCartService {


	List<Cart> findCartListByUserId(Long userId);

	void updateCartNum(Cart cart);

	void deleteCart(Cart cart);

	void addItem(Cart cart);

}
