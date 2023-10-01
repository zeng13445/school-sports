package com.formssi.zengzl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.formssi.zengzl.entity.SysUser;
import com.formssi.zengzl.entity.dto.UserDTO;

/**
 * @author RudeCrab
 * @description 用户业务接口
 */
public interface UserService {
    /**
     * 注册
     */
    void addUser(SysUser sysUser);

    /**
     * 注册
     */
    SysUser login(SysUser sysUser);

    /**
     * 获取用户信息
     * @return
     */
    SysUser getUserById(UserDTO userDTO);

    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<SysUser> listUser(IPage<SysUser> page);
}
