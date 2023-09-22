package com.formssi.zengzl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.formssi.zengzl.entity.SysUser;
import com.formssi.zengzl.entity.dto.UserDTO;
import com.formssi.zengzl.mapper.UserMapper;
import com.formssi.zengzl.service.UserService;
import org.springframework.stereotype.Service;

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
    public String addUser(SysUser sysUser) {
        // 直接编写业务逻辑
        //throw new APIException(-1, "错误");
        return "success";
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
