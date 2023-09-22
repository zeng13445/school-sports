package com.formssi.zengzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.formssi.zengzl.entity.SysUser;
import com.formssi.zengzl.entity.dto.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<SysUser> {

    SysUser getUserById(UserDTO userDTO);

    IPage<SysUser> listUser(IPage<SysUser> page);
}
