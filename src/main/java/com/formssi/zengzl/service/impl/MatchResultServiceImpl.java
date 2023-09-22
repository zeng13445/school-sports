package com.formssi.zengzl.service.impl;

import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.validator.ServiceAssert;
import com.formssi.zengzl.entity.MatchItem;
import com.formssi.zengzl.entity.MatchResult;
import com.formssi.zengzl.entity.dto.MatchItemDTO;
import com.formssi.zengzl.entity.dto.MatchResultDTO;
import com.formssi.zengzl.mapper.MatchResultMapper;
import com.formssi.zengzl.service.MatchResultService;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

@Service
public class MatchResultServiceImpl implements MatchResultService {
    private final MatchResultMapper matchResultMapper;

    public MatchResultServiceImpl(MatchResultMapper matchResultMapper) {
        this.matchResultMapper = matchResultMapper;
    }

    @Override
    public void recordResults(MatchResultDTO matchResultDTO) {
        // todo

        MatchResult matchResult = new MatchResult();
        BeanCopier copier = BeanCopier.create(MatchResultDTO.class, MatchResult.class, false);
        copier.copy(matchResultDTO, matchResult, null);

        matchResult.setRound(1);
        int insert = matchResultMapper.insert(matchResult);
        ServiceAssert.isTrue(insert > 0, ResultCode.CREATE_FAILED);
    }
}
