package com.company.springbootquickstart01.controller;

import com.company.springbootquickstart01.common.vo.JsonResult;
import com.company.springbootquickstart01.entity.UserDo;
import com.company.springbootquickstart01.param.UpdateUserParam;
import com.company.springbootquickstart01.service.UserService;
import com.company.springbootquickstart01.vo.UserVo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Api(tags = "用户操作接口")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findUserById")
    @ApiOperation("id查找单个用户")
    @ApiImplicitParam(name="id",value="用户id",required = true)
    public JsonResult<UserDo> findUserById(Long id) {
        UserDo userdo = userService.findUserById(id);
        return new JsonResult(userdo);
    }

    @PutMapping("/updateUser")
    @ApiOperation("修改用户")
    public JsonResult<UserVo> updateUser(@RequestBody UpdateUserParam param) {
        userService.updateUser(param);
        UserDo userdo = userService.getById(param.getId());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userdo, userVo);
        return new JsonResult(userVo);
    }
}
