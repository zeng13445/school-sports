package com.formssi.zengzl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.enums.UserTypeEnum;
import com.formssi.zengzl.base.utils.JwtUtil;
import com.formssi.zengzl.base.validator.ServiceAssert;
import com.formssi.zengzl.entity.SysUser;
import com.formssi.zengzl.entity.dto.UserDTO;
import com.formssi.zengzl.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

@Slf4j
@RestController
@Api(tags = "用户管理")
@RequestMapping("user")
public class UserController {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final RedisTemplate redisTemplate;

    public UserController(UserService userService, RedisTemplate redisTemplate, JwtUtil jwtUtil) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
        this.jwtUtil = jwtUtil;
    }

    @ApiOperation("注册普通运动员")
    @PostMapping("/register")
    public void addUser(@RequestBody @Valid SysUser sysUser) {
        userService.addUser(sysUser);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody @Valid SysUser sysUser) {
        SysUser login = userService.login(sysUser);

        String token = jwtUtil.createJWT(login.getId(), login.getUserName(), login.getUserType());
        HashMap<String, Object> map = new HashMap<>(7);
        map.put("token", token);
        map.put("roles", login.getUserType());
        map.put("userId", login.getId());
        return map;
    }

    @GetMapping("getUserById")
    @ApiOperation("获取用户信息")
    public SysUser getUserById(@Valid UserDTO userDTO) {
        return userService.getUserById(userDTO);
    }

    @GetMapping("listUser")
    @ApiOperation("获取所有用户信息")
    public IPage<SysUser> listUser(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   HttpServletRequest request) {

        String admin = (String) request.getAttribute(UserTypeEnum.ADMIN.name());
        String superAdmin = (String) request.getAttribute(UserTypeEnum.SUPER_ADMIN.name());
        ServiceAssert.isTrue(org.apache.commons.lang3.StringUtils.isNotEmpty(admin)
                || StringUtils.isNotEmpty(superAdmin), ResultCode.PERMISSION_NOT);

        IPage<SysUser> page = new Page<>(pageNum, pageSize);
        IPage<SysUser> userPage = userService.listUser(page);
        return userPage;
    }

}
