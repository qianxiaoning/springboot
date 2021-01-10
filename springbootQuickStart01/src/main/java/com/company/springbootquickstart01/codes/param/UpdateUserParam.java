package com.company.springbootquickstart01.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateUserParam implements Serializable {

    private static final long serialVersionUID = -7502281272213289161L;

    @ApiModelProperty(value = "用户id",required = true)
    private Long id;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "1男2女0未知")
    private Integer gender;

}
