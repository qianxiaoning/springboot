package com.qxn.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.qxn.pojo.BasePojo;
import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@TableName("c2csystem_item_desc")
public class ProductDesc extends BasePojo {
    @TableId//id与item id一致
    private Long itemId;
    private String itemDesc;
}
