package com.formssi.zengzl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.validator.ServiceAssert;
import com.formssi.zengzl.entity.MatchResult;
import com.formssi.zengzl.entity.dto.MatchResultDTO;
import com.formssi.zengzl.entity.vo.MatchResultVO;
import com.formssi.zengzl.mapper.MatchResultMapper;
import com.formssi.zengzl.service.MatchParticipationService;
import com.formssi.zengzl.service.MatchResultService;
import java.math.BigDecimal;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class MatchResultServiceImpl implements MatchResultService {
    private final MatchParticipationService matchParticipationService;
    private final MatchResultMapper matchResultMapper;

    public MatchResultServiceImpl(MatchResultMapper matchResultMapper,
            MatchParticipationService matchParticipationService) {
        this.matchResultMapper = matchResultMapper;
        this.matchParticipationService = matchParticipationService;
    }

    @Override
    public void recordResults(MatchResultDTO matchResultDTO) {
        // 0.校验用户是否参赛了
        Boolean valid = matchParticipationService.validParticipation(matchResultDTO.getUserId(),
                matchResultDTO.getMatchId());
        ServiceAssert.isTrue(valid, ResultCode.VALIDATE_FAILED, "用户未报名不能参赛");

        // 1.获取该用户该比赛的最大轮次
        LambdaQueryWrapper<MatchResult> lambdaQueryWrapper = Wrappers.lambdaQuery(MatchResult.class);
        lambdaQueryWrapper.eq(MatchResult::getUserId, matchResultDTO.getUserId())
                .eq(MatchResult::getMatchId, matchResultDTO.getMatchId())
                .orderByDesc(MatchResult::getRound)
                .last("LIMIT 1");
        MatchResult result = matchResultMapper.selectOne(lambdaQueryWrapper);
        Integer round = 0;
        if (!ObjectUtils.isEmpty(result)) {
            round = result.getRound();
        }

        // 2.赋值落库
        MatchResult matchResult = new MatchResult();
        BeanCopier copier = BeanCopier.create(MatchResultDTO.class, MatchResult.class, false);
        copier.copy(matchResultDTO, matchResult, null);
        matchResult.setRound(round + 1);
        int insert = matchResultMapper.insert(matchResult);
        ServiceAssert.isTrue(insert > 0, ResultCode.CREATE_FAILED);
    }

    @Override
    public void updateResults(Long resultId, BigDecimal score) {
        MatchResult matchResult = new MatchResult();
        matchResult.setId(resultId);
        matchResult.setScore(score);

        int insert = matchResultMapper.updateById(matchResult);
        ServiceAssert.isTrue(insert > 0, ResultCode.CREATE_FAILED);

    }

    @Override
    public IPage<MatchResultVO> getResults(IPage<MatchResultVO> page, MatchResult matchResult) {
        return matchResultMapper.getResults(page, matchResult);
    }

    @Override
    public IPage<MatchResultVO> getRank(IPage<MatchResultVO> page, Long matchId, Integer round) {
        return matchResultMapper.getRank(page, matchId, round);
    }
}
