package com.company.springbootquickstart01.libs.loginRegisterProcess.normalWay;

import com.company.springbootquickstart01.codes.common.vo.JsonResult;
import com.company.springbootquickstart01.codes.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@Api(tags = "用户操作接口")
@RequestMapping("/api/user")
public class NormalWayController {
    @Autowired
    private NormalWayService normalWayService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public JsonResult register(@RequestBody NormalWayParam param) {
        normalWayService.register(param);
        return new JsonResult("注册成功");
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public JsonResult<LoginVo> login(@RequestBody NormalWayParam param, HttpServletRequest request,HttpServletResponse response) {
        LoginVo vo = normalWayService.login(param,request,response);
        return new JsonResult(vo);
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public JsonResult logout(HttpServletRequest request,HttpServletResponse response) {
        normalWayService.logout(request,response);
        return new JsonResult("退出成功");
    }

}
