package com.company.springbootquickstart01.codes.service;

import com.company.springbootquickstart01.codes.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.springbootquickstart01.codes.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 附件表 服务类
 * </p>
 *
 * @author Shon Qian
 * @since 2021-03-01
 */
public interface FileService extends IService<File> {

    List<FileVo> uploadFiles(MultipartFile[] multipartFiles);
}
