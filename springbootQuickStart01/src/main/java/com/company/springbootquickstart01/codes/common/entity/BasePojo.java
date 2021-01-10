package com.company.springbootquickstart01.codes.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

//pojo基类，公共属性、实现序列化
@Data
@Accessors(chain=true)
public class BasePojo implements Serializable{
    private static final long serialVersionUID = 7378742805362932715L;
//    private Date createTime;
//
//    private Long createBy;
//
//    private Long updateBy;
//
//    private Date updateTime;
//
//    private Long delBy;
//
//    private Date delTime;
}
