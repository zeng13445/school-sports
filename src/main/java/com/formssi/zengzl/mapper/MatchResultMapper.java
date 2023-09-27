package com.formssi.zengzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.formssi.zengzl.entity.MatchResult;
import com.formssi.zengzl.entity.vo.MatchResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchResultMapper extends BaseMapper<MatchResult> {

    /**
     * 动态查询成绩
     */
    IPage<MatchResultVO> getResults(Page<MatchResultVO> page, @Param("data") MatchResult matchResult);

    /**
     * 查询排名
     */
    IPage<MatchResultVO> getRank(Page<MatchResultVO> page,
            @Param("matchId") Long matchId,
            @Param("round") Integer round);

}
