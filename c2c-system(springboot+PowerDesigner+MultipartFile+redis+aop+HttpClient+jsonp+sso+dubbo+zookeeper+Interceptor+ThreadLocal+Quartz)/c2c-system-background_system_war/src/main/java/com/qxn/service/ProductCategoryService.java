package com.qxn.service;

import com.qxn.pojo.ProductCategory;
import com.qxn.vo.UITree;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findProductCategoryById(Long productCategoryId);

    List<UITree> findUITreeList(Long parentId);

    List<UITree> findUITreeCacheList(Long parentId);
}
