package com.formssi.zengzl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.exception.APIException;
import com.formssi.zengzl.base.validator.ServiceAssert;
import com.formssi.zengzl.entity.SysUser;
import com.formssi.zengzl.entity.dto.UserDTO;
import com.formssi.zengzl.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(tags = "用户接口")
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final RedisTemplate redisTemplate;

    public UserController(UserService userService, RedisTemplate redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation("注册普通运动员")
    @PostMapping
    public void addUser(@RequestBody @Valid SysUser sysUser) {
//        Object name = redisTemplate.opsForValue().get("name");
//        System.out.println(name);
        userService.addUser(sysUser);
    }

    @ApiOperation("获得单个用户")
    @GetMapping("/getUser")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "a", required = true, value = "用户id"),
            @ApiImplicitParam(name = "b", value = "密码")
    })
    //a必传，b选传，这种场景用于表单传参，而且可以控制参数是否需要传入以及有注释。
    public SysUser getUser(@RequestParam Integer a, Integer b) {
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        //sysUser.setAccount("12345678");
        sysUser.setPassword("12345678");
        //sysUser.setEmail("123@qq.com");
        return sysUser;
    }

    /**
     * 测试断言 和 非空判断 和 抛出自定义异常
     */
    @ApiOperation("测试断言和非空判断")
    @GetMapping("/test")
    public void getUser(String name) {
        // 0.test日志（logback+slf4j）
        log.info("我叫{}", "曾子龙");
        log.error("身高{}", 164);
        log.debug("籍贯{}", "抚州");
        log.warn("喜好{}", "篮球");

        // 1.测试断言，使用spring的工具
        //Assert.notNull(name, "名字不能为空");
        //Assert.isTrue(false, "要为true的");
        ServiceAssert.notNull(name, ResultCode.VALIDATE_FAILED);

        // 2.测试非空判断
        if (StringUtils.isEmpty(name)) {
            // do something
            System.out.println("为空，执行接下来操作");
        } else {
            System.out.println("有值，执行接下来操作");

            // 3.测试抛出自定义异常
            // 默认提示1001相应失败 throw new APIException();
            throw new APIException("测试test");
        }
    }

    @GetMapping("getUserById")
    @ApiOperation("获取用户信息")
    public SysUser getUserById(@Valid UserDTO userDTO) {
        return userService.getUserById(userDTO);
    }

    @GetMapping("listUser")
    @ApiOperation("获取所有用户信息")
    public IPage<SysUser> listUser(Integer pageNum, Integer pageSize) {
        IPage<SysUser> page = new Page<>(pageNum, pageSize);
        IPage<SysUser> userPage = userService.listUser(page);
        return userPage;
    }

}
