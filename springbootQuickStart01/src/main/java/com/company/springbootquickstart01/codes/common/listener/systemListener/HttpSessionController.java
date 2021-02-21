package com.company.springbootquickstart01.codes.common.listener.systemListener;

import com.company.springbootquickstart01.codes.common.vo.JsonResult;
import com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay.NormalWayParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Api(tags = "监听器测试")
@RequestMapping("/api/listener")
public class HttpSessionController {

    @GetMapping("/getPv")
    @ApiOperation("获取pv")
    public JsonResult getPv(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Object pv = session.getServletContext().getAttribute("pv");
        return new JsonResult("pv数 = " + pv);
    }

    @GetMapping("/getUserInfoByServletContext")
    @ApiOperation("从ServletContext中获取初始化数据")
    public JsonResult getUserInfoByServletContext(HttpServletRequest httpServletRequest) {
        ServletContext servletContext = httpServletRequest.getServletContext();
        Object userInfo = servletContext.getAttribute("userInfo");
        return new JsonResult(userInfo);
    }
}
