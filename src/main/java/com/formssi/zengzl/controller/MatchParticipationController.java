package com.formssi.zengzl.controller;

import com.formssi.zengzl.entity.MatchParticipation;
import com.formssi.zengzl.service.MatchParticipationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "报名接口")
@RequestMapping("matchParticipation")
public class MatchParticipationController {
    @Autowired
    private MatchParticipationService matchParticipationService;

    @ApiOperation("参加比赛")
    @PostMapping
    public void joinMatch(@RequestBody MatchParticipation matchParticipation) {
        matchParticipationService.joinMatch(matchParticipation);
    }

}
