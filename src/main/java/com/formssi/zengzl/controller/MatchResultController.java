package com.formssi.zengzl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.formssi.zengzl.entity.MatchResult;
import com.formssi.zengzl.entity.dto.MatchResultDTO;
import com.formssi.zengzl.entity.vo.MatchResultVO;
import com.formssi.zengzl.service.MatchResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @ApiOperation("修改成绩")
    @PatchMapping
    public void updateResults(@RequestParam Long resultId, @RequestParam String score) {
        matchResultService.updateResults(resultId, score);
    }

    @ApiOperation("分页查询成绩")
    @GetMapping
    public IPage<MatchResultVO> getResults(MatchResult matchResult,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        Page<MatchResultVO> page = new Page<>(pageNum, pageSize);
        return matchResultService.getResults(page, matchResult);
    }

}
