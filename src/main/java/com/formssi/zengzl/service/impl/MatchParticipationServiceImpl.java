package com.formssi.zengzl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.utils.DateUtils;
import com.formssi.zengzl.base.validator.ServiceAssert;
import com.formssi.zengzl.entity.MatchItem;
import com.formssi.zengzl.entity.MatchParticipation;
import com.formssi.zengzl.entity.vo.MatchParticipationVO;
import com.formssi.zengzl.mapper.MatchParticipationMapper;
import com.formssi.zengzl.service.MatchItemService;
import com.formssi.zengzl.service.MatchParticipationService;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Service
public class MatchParticipationServiceImpl implements MatchParticipationService {
    private final MatchItemService matchItemService;
    private final MatchParticipationMapper matchParticipationMapper;

    public MatchParticipationServiceImpl(MatchParticipationMapper matchParticipationMapper,
            MatchItemService matchItemService) {
        this.matchParticipationMapper = matchParticipationMapper;
        this.matchItemService = matchItemService;
    }

    @Override
    public void joinMatch(MatchParticipation matchParticipation) {
        ServiceAssert.notNull(matchParticipation.getMatchId(), ResultCode.VALIDATE_FAILED, "赛事ID不为空");
        ServiceAssert.notNull(matchParticipation.getUserId(), ResultCode.VALIDATE_FAILED, "用户ID不为空");

        // 1.校验报名时间
        MatchItem matchItem = matchItemService.matchItemDetails(matchParticipation.getMatchId());
        Date currentTime = new Date();
        boolean start = DateUtils.isBefore(matchItem.getApplyStartTime(), currentTime);
        ServiceAssert.isTrue(start, ResultCode.APPLY_NOT_START);
        boolean end = DateUtils.isBefore(currentTime,  matchItem.getApplyEndTime());
        ServiceAssert.isTrue(end, ResultCode.APPLY_HAS_ENDED);

        // 2.用户是否已参赛校验
        LambdaQueryWrapper<MatchParticipation> lambdaQueryWrapper = Wrappers.lambdaQuery(MatchParticipation.class);
        lambdaQueryWrapper.eq(MatchParticipation::getUserId, matchParticipation.getUserId())
                .eq(MatchParticipation::getMatchId, matchParticipation.getMatchId());
        List<MatchParticipation> matchParticipations = matchParticipationMapper.selectList(lambdaQueryWrapper);
        ServiceAssert.isTrue(CollectionUtils.isEmpty(matchParticipations), ResultCode.JOIN_EXISTED);

        // 3.参赛
        int insert = matchParticipationMapper.insert(matchParticipation);
        ServiceAssert.isTrue(insert > 0, ResultCode.CREATE_FAILED);
    }

    @Override
    public Boolean validParticipation(Long userId, Long matchId) {
        LambdaQueryWrapper<MatchParticipation> lambdaQueryWrapper = Wrappers.lambdaQuery(MatchParticipation.class);
        lambdaQueryWrapper.eq(MatchParticipation::getUserId, userId)
                .eq(MatchParticipation::getMatchId, matchId);
        MatchParticipation matchParticipation = matchParticipationMapper.selectOne(lambdaQueryWrapper);
        if (ObjectUtils.isEmpty(matchParticipation)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public IPage<MatchParticipationVO> getParticipation(IPage<MatchParticipation> page, Long matchId) {
        return matchParticipationMapper.getParticipation(page, matchId);
    }
}
