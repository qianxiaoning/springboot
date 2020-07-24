package com.qxn.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qxn.annotation.CacheFind;
import com.qxn.mapper.ProductCategoryMapper;
import com.qxn.pojo.ProductCategory;
import com.qxn.util.ObjectMapperUtil;
import com.qxn.vo.UITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	//	@Autowired//findUITreeCacheList缓存不写在业务代码中
	private Jedis jedis;

	@Override
	public ProductCategory findProductCategoryById(Long productCategoryId) {
		return productCategoryMapper.selectById(productCategoryId);
	}

	@Override
	@CacheFind
	public List<UITree> findUITreeList(Long parentId) {
		List<ProductCategory> list = findProductCategoryList(parentId);
		List<UITree> uiTrees = new ArrayList<>();
		for (ProductCategory productCategory : list) {
			long id = productCategory.getId();
			String text = productCategory.getName();
			//如果是父级closed，不是open
			String state = productCategory.getIsParent()?"closed":"open";
			UITree uiTree = new UITree(id, text, state);
			uiTrees.add(uiTree);
		}
		return uiTrees;
	}

	@Override
	public List<UITree> findUITreeCacheList(Long parentId) {
		//为了标识，命名"PRODUCT_CATEGORY_3"
		String key = "PRODUCT_CATEGORY_" + parentId;
		//1.查询缓存 jedis.get(parentId)
		String result = jedis.get(key);
		//2.空 查数据库findUITreeList()，将数据保存到redis中
		//jedis.set(parentId,数据转化为json) return 数据
		//3.非空 缓存中有数据（json串）return json串转化为对象
		List<UITree> treeList = new ArrayList<>();
		if(StringUtils.isEmpty(result)) {
			treeList = findUITreeList(parentId);
			jedis.set(key, ObjectMapperUtil.toJSON(treeList));
			System.out.println("走数据库");
		}else {
			treeList = ObjectMapperUtil.toObject(result, treeList.getClass());
			System.out.println("redis缓存查询");
		}
		return treeList;
	}

	public List<ProductCategory> findProductCategoryList(Long parentId) {
		QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id",parentId);
		List<ProductCategory> list = productCategoryMapper.selectList(queryWrapper);
		return list;
	}

}
