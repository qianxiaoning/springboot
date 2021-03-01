package com.company.springbootquickstart01.codes.controller;
import com.company.springbootquickstart01.codes.common.vo.JsonResult;
import com.company.springbootquickstart01.codes.entity.File;
import com.company.springbootquickstart01.codes.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.company.springbootquickstart01.codes.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author Shon Qian
 * @since 2021-03-01
 */
@RestController
@Api(tags = "公告模块")
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFiles")
    //spring图片上传接口，spring.MultipartFile
    public JsonResult<List<FileVo>> uploadFiles(MultipartFile[] multipartFiles) {
        List<FileVo> list = fileService.uploadFiles(multipartFiles);
        return new JsonResult(list);
    }
}
