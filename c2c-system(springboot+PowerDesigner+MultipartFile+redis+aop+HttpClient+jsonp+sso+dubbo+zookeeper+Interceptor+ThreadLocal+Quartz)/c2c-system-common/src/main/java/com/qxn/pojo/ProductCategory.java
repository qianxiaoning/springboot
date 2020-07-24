package com.qxn.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("c2csystem_item_cat")
public class ProductCategory extends BasePojo {
    @TableId(type = IdType.AUTO)//主键自增
    private long id;//商品分类id
    private long parentId;//父分类id
    private String name;//分类名称
    private Integer status;//商品分类状态
    private Integer sortOrder;//排序号
    private Boolean isParent;//是否为父级
}
