package com.qxn.springbootmybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
//@TableName(value = "user")
//@TableName("user")
@TableName//表名和类名一致时
public class User implements Serializable {
    private static final long serialVersionUID = 8741640020893674441L;
    @TableId(type = IdType.AUTO)//主键，自增
    private Integer id;
    private String name;
    //和数据库表名不一致时
//    @TableField(value = "name")
//    private String username;
}
