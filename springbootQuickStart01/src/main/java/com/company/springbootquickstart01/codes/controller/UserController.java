package com.company.springbootquickstart01.codes.controller;

import com.company.springbootquickstart01.codes.common.vo.JsonResult;
import com.company.springbootquickstart01.codes.entity.UserDo;
import com.company.springbootquickstart01.codes.param.UpdateUserParam;
import com.company.springbootquickstart01.codes.service.UserService;
import com.company.springbootquickstart01.codes.vo.UserVo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
