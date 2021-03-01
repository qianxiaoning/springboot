package com.company.springbootquickstart01.codes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author Shon Qian
 * @since 2021-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="File对象", description="附件表")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "文件大小")
    private Long fileSize;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @ApiModelProperty(value = "文件全名")
    private String fullFileName;

    @ApiModelProperty(value = "本地路径 ，此处不存")
    private String localPath;

    @ApiModelProperty(value = "oss 存储空间名称")
    private String bucketName;

    @ApiModelProperty(value = "oss 资源名称")
    private String objectName;

    @ApiModelProperty(value = "oss 资源存储目录路径")
    private String ossFilePath;

    @ApiModelProperty(value = "oss 资源的实际访问地址")
    private String ossUrl;

    private Long createBy;

    private LocalDateTime createTime;

    private Long updateBy;

    private LocalDateTime updateTime;

    private Long delBy;

    private LocalDateTime delTime;

    @ApiModelProperty(value = "0表示有效1表示无效")
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty(value = "版本信息")
    @Version
    private Integer version;


}
