package com.company.springbootquickstart01.codes.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.company.springbootquickstart01.codes.service.LogService;
import io.swagger.annotations.Api;

/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author Shon Qian
 * @since 2021-02-28
 */
@RestController
@Api(tags = "未分类接口")
@RequestMapping("/api/Log")
public class LogController {
    @Autowired
    private LogService logService;
}
