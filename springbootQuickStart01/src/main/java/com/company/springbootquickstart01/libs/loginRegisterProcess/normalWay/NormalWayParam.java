package com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class NormalWayParam implements Serializable {

    private static final long serialVersionUID = -7502281272213289161L;

    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "账号")
    private String account;

    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

}
