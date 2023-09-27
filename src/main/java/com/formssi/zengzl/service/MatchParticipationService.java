package com.formssi.zengzl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.formssi.zengzl.entity.MatchParticipation;
import com.formssi.zengzl.entity.vo.MatchParticipationVO;

public interface MatchParticipationService {

    /**
     * 参加比赛
     */
    void joinMatch(MatchParticipation matchParticipation);

    /**
     * 校验用户是否参赛
     */
    Boolean validParticipation(Long userId, Long matchId);

    /**
     * 查询报名信息
     */
    IPage<MatchParticipationVO> getParticipation(Page<MatchParticipation> page, Long matchId);
}
