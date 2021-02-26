package com.company.springbootquickstart01.codes.common.filter;

import com.company.springbootquickstart01.codes.common.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Api(tags = "过滤器测试")
@RequestMapping("/api/filter")
public class FilterController {
    @GetMapping("/filterTest")
    @ApiOperation("走过滤器")
    public JsonResult filterTest() {
        return new JsonResult();
    }
}
