package com.formssi.zengzl.controller;

import com.formssi.zengzl.entity.dto.MatchResultDTO;
import com.formssi.zengzl.service.MatchResultService;
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
@Api(tags = "结果接口")
@RequestMapping("matchResult")
public class MatchResultController {
    private final MatchResultService matchResultService;

    public MatchResultController(MatchResultService matchResultService) {
        this.matchResultService = matchResultService;
    }

    @ApiOperation("记录成绩")
    @PostMapping
    public void recordResults(@RequestBody @Valid MatchResultDTO matchResult) {
        matchResultService.recordResults(matchResult);
    }

}
