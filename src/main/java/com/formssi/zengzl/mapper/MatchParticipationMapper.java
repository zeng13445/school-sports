package com.formssi.zengzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.formssi.zengzl.entity.MatchParticipation;
import com.formssi.zengzl.entity.vo.MatchParticipationVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchParticipationMapper extends BaseMapper<MatchParticipation> {
    IPage<MatchParticipationVO> getParticipation(IPage<MatchParticipation> page, @Param("matchId") Long matchId);

}
