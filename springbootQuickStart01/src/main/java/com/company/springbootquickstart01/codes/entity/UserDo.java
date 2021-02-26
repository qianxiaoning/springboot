package com.company.springbootquickstart01.codes.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("user")
@ApiModel(value="user对象", description="")
public class UserDo extends Model<UserDo> {

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "账户名")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "电话号码")
    private String telNum;

    @ApiModelProperty(value = "1男2女3未知")
    private Integer gender;

    @ApiModelProperty(value = "头像信息-资源id")
    private String avatarResourceId;

    @ApiModelProperty(value = "用户状态0表示启用1表示禁用")
    private Integer userStatus;

    @ApiModelProperty(value = "最近登录时间")
    private Date lastLoginTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Long delBy;

    private Date delTime;

    @ApiModelProperty(value = "0表示有效1表示无效")
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty(value = "版本信息")
    @Version
    private Integer version;

}
