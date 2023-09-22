package com.formssi.zengzl.service.impl;

import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.validator.ServiceAssert;
import com.formssi.zengzl.entity.MatchParticipation;
import com.formssi.zengzl.mapper.MatchParticipationMapper;
import com.formssi.zengzl.service.MatchParticipationService;
import org.springframework.stereotype.Service;

@Service
public class MatchParticipationServiceImpl implements MatchParticipationService {
    private final MatchParticipationMapper matchParticipationMapper;

    public MatchParticipationServiceImpl(MatchParticipationMapper matchParticipationMapper) {
        this.matchParticipationMapper = matchParticipationMapper;
    }

    @Override
    public void joinMatch(MatchParticipation matchParticipation) {
        ServiceAssert.notNull(matchParticipation.getMatchId(), ResultCode.VALIDATE_FAILED);
        ServiceAssert.notNull(matchParticipation.getUserId(), ResultCode.VALIDATE_FAILED);

        int insert = matchParticipationMapper.insert(matchParticipation);
        ServiceAssert.isTrue(insert > 0, ResultCode.CREATE_FAILED);
    }
}
