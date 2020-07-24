package com.qxn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qxn.pojo.Product;
import com.qxn.vo.ProductAndCategoryName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product>{
    @Select("SELECT i.*,c.name categoryName FROM c2csystem_item i left join c2csystem_item_cat c on i.cid=c.id ORDER BY updated DESC LIMIT #{start},#{rows}")
    List<ProductAndCategoryName> findProductByPage(@Param("start") Integer start, @Param("rows") Integer rows);
    @Select("SELECT * FROM c2csystem_item ORDER BY updated DESC LIMIT #{start},#{rows}")
    List<Product> findProducts(int start, Integer rows);
}
