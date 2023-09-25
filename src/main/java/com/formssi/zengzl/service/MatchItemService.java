package com.formssi.zengzl.service;

import com.formssi.zengzl.entity.MatchItem;
import com.formssi.zengzl.entity.dto.MatchItemDTO;

public interface MatchItemService {

    /**
     * 创建比赛
     */
    void createMatchItem(MatchItemDTO matchItemDTO);

    /**
     * 查询比赛详情
     */
    MatchItem matchItemDetails(Long matchId);

}
