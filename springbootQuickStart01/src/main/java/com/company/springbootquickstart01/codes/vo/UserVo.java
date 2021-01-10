package com.company.springbootquickstart01.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = -7463765904898109995L;

    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "昵称")
    private String nickName;

}