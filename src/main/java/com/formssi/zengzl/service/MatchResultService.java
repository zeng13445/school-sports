package com.formssi.zengzl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.formssi.zengzl.entity.MatchResult;
import com.formssi.zengzl.entity.dto.MatchResultDTO;
import com.formssi.zengzl.entity.vo.MatchResultVO;

public interface MatchResultService {

    /**
     * 记录成绩
     */
    void recordResults(MatchResultDTO matchResultDTO);

    /**
     * 更新成绩
     */
    void updateResults(Long resultId, String score);

    /**
     * 动态查询成绩
     */
    IPage<MatchResultVO> getResults(Page<MatchResultVO> page, MatchResult matchResult);

}