package com.formssi.zengzl.full;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.formssi.zengzl.entity.SysUser;
import com.formssi.zengzl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用户服务单元测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSysUserService {
    @Autowired
    private UserService userService;

    @Test
    public void listUser() {
        IPage<SysUser> userIPage = new Page<>(1, 10);
        IPage<SysUser> userInfo = userService.listUser(userIPage);

        log.info("用户分页信息{}", userInfo.getRecords());
    }
}
