package com.formssi.zengzl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.formssi.zengzl.entity.MatchParticipation;
import com.formssi.zengzl.entity.vo.MatchParticipationVO;
import com.formssi.zengzl.service.MatchParticipationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "报名管理")
@RequestMapping("/matchParticipation")
public class MatchParticipationController {
    private final MatchParticipationService matchParticipationService;

    public MatchParticipationController(MatchParticipationService matchParticipationService) {
        this.matchParticipationService = matchParticipationService;
    }

    @ApiOperation("参加比赛")
    @PostMapping
    public void joinMatch(@RequestBody MatchParticipation matchParticipation) {
        matchParticipationService.joinMatch(matchParticipation);
    }

    @ApiOperation("查询报名信息")
    @GetMapping
    public IPage<MatchParticipationVO> getParticipation(@RequestParam Long matchId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        IPage<MatchParticipation> page = new Page<>(pageNum, pageSize);
        return matchParticipationService.getParticipation(page, matchId);
    }

}
