package com.formssi.zengzl.controller;

import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.enums.UserTypeEnum;
import com.formssi.zengzl.base.validator.ServiceAssert;
import com.formssi.zengzl.entity.dto.MatchItemDTO;
import com.formssi.zengzl.service.MatchItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "比赛管理")
@RequestMapping("/matchItem")
public class MatchItemController {
    private final MatchItemService matchItemService;

    public MatchItemController(MatchItemService matchItemService) {
        this.matchItemService = matchItemService;
    }

    @ApiOperation("创建比赛")
    @PostMapping
    public void createMatchItem(@RequestBody @Valid MatchItemDTO matchItemDTO,
                                HttpServletRequest request) {

        String admin = (String) request.getAttribute(UserTypeEnum.ADMIN.name());
        String superAdmin = (String) request.getAttribute(UserTypeEnum.SUPER_ADMIN.name());
        ServiceAssert.isTrue(StringUtils.isNotEmpty(admin)
                || StringUtils.isNotEmpty(superAdmin), ResultCode.PERMISSION_NOT);

        matchItemService.createMatchItem(matchItemDTO);
    }

}
