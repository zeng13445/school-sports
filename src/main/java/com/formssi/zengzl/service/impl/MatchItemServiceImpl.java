package com.formssi.zengzl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.formssi.zengzl.base.enums.ResultCode;
import com.formssi.zengzl.base.validator.ServiceAssert;
import com.formssi.zengzl.entity.MatchItem;
import com.formssi.zengzl.entity.dto.MatchItemDTO;
import com.formssi.zengzl.mapper.MatchItemMapper;
import com.formssi.zengzl.service.MatchItemService;
import java.util.List;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class MatchItemServiceImpl implements MatchItemService {
    private final MatchItemMapper matchItemMapper;
    public MatchItemServiceImpl(MatchItemMapper matchItemMapper) {
        this.matchItemMapper = matchItemMapper;
    }


    @Override
    public void createMatchItem(MatchItemDTO matchItemDTO) {
        // 1.校验重复
        LambdaQueryWrapper<MatchItem> lambdaQueryWrapper = Wrappers.lambdaQuery(MatchItem.class);
        lambdaQueryWrapper.eq(MatchItem::getMatchName, matchItemDTO.getMatchName());
        List<MatchItem> matchItems = matchItemMapper.selectList(lambdaQueryWrapper);
        ServiceAssert.isTrue(CollectionUtils.isEmpty(matchItems), ResultCode.DATA_EXISTED,
                "已有名为" + matchItemDTO.getMatchName() + "的比赛");

        // 2.新增
        MatchItem matchItem = new MatchItem();
        BeanCopier copier = BeanCopier.create(MatchItemDTO.class, MatchItem.class, false);
        copier.copy(matchItemDTO, matchItem, null);

        int insert = matchItemMapper.insert(matchItem);
        ServiceAssert.isTrue(insert > 0, ResultCode.CREATE_FAILED);
    }

    @Override
    public MatchItem matchItemDetails(Long matchId) {
        return matchItemMapper.selectById(matchId);
    }
}
