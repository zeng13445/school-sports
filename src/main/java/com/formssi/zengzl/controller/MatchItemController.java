package com.formssi.zengzl.controller;

import com.formssi.zengzl.entity.dto.MatchItemDTO;
import com.formssi.zengzl.service.MatchItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "比赛接口")
@RequestMapping("matchItem")
public class MatchItemController {
    private final MatchItemService matchItemService;

    public MatchItemController(MatchItemService matchItemService) {
        this.matchItemService = matchItemService;
    }

    @ApiOperation("创建比赛")
    @PostMapping
    public void createMatchItem(@RequestBody @Valid MatchItemDTO matchItemDTO) {
        matchItemService.createMatchItem(matchItemDTO);
    }

}
