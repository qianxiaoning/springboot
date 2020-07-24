package com.qxn.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qxn.dubbo.service.DubboCartService;
import com.qxn.mapper.CartMapper;
import com.qxn.pojo.Cart;

@Service
public class DubboCartServiceImpl implements DubboCartService{
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> findCartListByUserId(Long userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
        queryWrapper.eq("user_id",userId);
        return cartMapper.selectList(queryWrapper);
    }

    @Override
    public void updateCartNum(Cart cart) {
        //update c2csystem_cart set num=#{num},updated=#{updated}
        //where user_id=#{userId} and item_id=#{itemId}
        Cart cartTemp = new Cart();
        cartTemp.setNum(cart.getNum())
                .setUpdated(new Date());
        UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<Cart>();
        updateWrapper.eq("item_id", cart.getItemId())
                .eq("user_id", cart.getUserId());
        cartMapper.update(cartTemp, updateWrapper);
    }

    @Override
    public void deleteCart(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>(cart);
        cartMapper.delete(queryWrapper);
    }

    @Override
    public void addItem(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
        queryWrapper.eq("item_id",cart.getItemId())
                .eq("user_id",cart.getUserId());
        Cart lastOne = cartMapper.selectOne(queryWrapper);

        if(lastOne == null) {
            //无记录增加
            cart.setCreated(new Date())
                    .setUpdated(cart.getCreated());
            cartMapper.insert(cart);
        }else {
            //有记录更新num和updated
            Integer num = cart.getNum()+lastOne.getNum();
            Cart cartTemp = new Cart();
            cartTemp.setId(lastOne.getId())
                    .setNum(num)
                    .setUpdated(new Date());
            cartMapper.updateById(cartTemp);
        }
    }
}
