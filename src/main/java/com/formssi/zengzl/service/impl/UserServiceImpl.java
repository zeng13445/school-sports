package com.formssi.zengzl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.enums.UserTypeEnum;
import com.formssi.zengzl.base.validator.ServiceAssert;
import com.formssi.zengzl.entity.SysUser;
import com.formssi.zengzl.entity.dto.UserDTO;
import com.formssi.zengzl.mapper.UserMapper;
import com.formssi.zengzl.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author RC
 * @description 用户业务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(SysUser sysUser) {
        // 1.校验
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = Wrappers.lambdaQuery(SysUser.class);
        lambdaQueryWrapper.eq(SysUser::getUserName, sysUser.getUserName());
        SysUser valid = userMapper.selectOne(lambdaQueryWrapper);
        ServiceAssert.isTrue(ObjectUtils.isEmpty(valid), ResultCode.VALIDATE_FAILED, "账户已存在");

        // 2.创建
        sysUser.setUserType(UserTypeEnum.ATHLETE.ordinal());
        int insert = userMapper.insert(sysUser);
        ServiceAssert.isTrue(insert > 0, ResultCode.CREATE_FAILED);
    }

    @Override
    public SysUser login(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = Wrappers.lambdaQuery(SysUser.class);
        lambdaQueryWrapper.eq(SysUser::getUserName, sysUser.getUserName());
        SysUser userInfo = userMapper.selectOne(lambdaQueryWrapper);
        ServiceAssert.isTrue(!ObjectUtils.isEmpty(userInfo), ResultCode.VALIDATE_FAILED, "账户不存在");
        ServiceAssert.isTrue(userInfo.getPassword().equals(sysUser.getPassword()), ResultCode.VALIDATE_FAILED, "密码错误");

        return userInfo;
    }

    @Override
    public SysUser getUserById(UserDTO userDTO) {
        return userMapper.getUserById(userDTO);
    }

    @Override
    public IPage<SysUser> listUser(IPage<SysUser> page) {
        return userMapper.listUser(page);
    }
}
