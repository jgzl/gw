package com.github.jgzl.gw.gateway.admin.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.jgzl.gw.common.model.errorcode.dataobject.ErrorCodeDO;
import com.github.jgzl.gw.common.data.mapper.ExtendBaseMapper;
import com.github.jgzl.gw.common.data.query.QueryWrapperX;
import com.github.jgzl.gw.common.model.errorcode.vo.ErrorCodeExportReqVO;
import com.github.jgzl.gw.common.model.errorcode.vo.ErrorCodePageReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Mapper
public interface ErrorCodeMapper extends ExtendBaseMapper<ErrorCodeDO> {

    default IPage<ErrorCodeDO> selectPage(ErrorCodePageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<ErrorCodeDO>()
                .eqIfPresent("type", reqVO.getType())
                .likeIfPresent("application_name", reqVO.getApplicationName())
                .eqIfPresent("code", reqVO.getCode())
                .likeIfPresent("message", reqVO.getMessage())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByAsc("application_name", "code"));
    }

    default List<ErrorCodeDO> selectList(ErrorCodeExportReqVO reqVO) {
        return selectList(new QueryWrapperX<ErrorCodeDO>()
                .eqIfPresent("type", reqVO.getType())
                .likeIfPresent("application_name", reqVO.getApplicationName())
                .eqIfPresent("code", reqVO.getCode())
                .likeIfPresent("message", reqVO.getMessage())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByAsc("application_name", "code"));
    }

    default List<ErrorCodeDO> selectListByCodes(Collection<Integer> codes) {
        return selectList(new QueryWrapper<ErrorCodeDO>().in("code", codes));
    }

    default ErrorCodeDO selectByCode(Integer code) {
        return selectOne(new QueryWrapper<ErrorCodeDO>().eq("code", code));
    }

    default List<ErrorCodeDO> selectListByApplicationNameAndUpdateTimeGt(String applicationName, Date minUpdateTime) {
        return selectList(new QueryWrapperX<ErrorCodeDO>().eq("application_name", applicationName)
                .gtIfPresent("update_time", minUpdateTime));
    }

}
