package com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = -7463765904898109995L;

    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "账户名")
    private String account;

}