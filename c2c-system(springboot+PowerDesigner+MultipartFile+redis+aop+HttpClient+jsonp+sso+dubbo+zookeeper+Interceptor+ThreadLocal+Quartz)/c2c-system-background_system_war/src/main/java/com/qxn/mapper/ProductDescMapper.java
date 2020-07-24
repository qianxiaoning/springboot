package com.qxn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qxn.pojo.Product;
import com.qxn.pojo.ProductDesc;
import com.qxn.vo.ProductAndCategoryName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductDescMapper extends BaseMapper<ProductDesc>{

}
