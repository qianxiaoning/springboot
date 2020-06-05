package com.qxn.springbootmybatis.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -3884293048139287375L;
    private Integer id;
    private String name;
}
