package com.github.gw.common.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 扩展通用 Mapper，支持数据权限 和批量插入
 *
 * @author li7hai26@gmail.com
 * @date 2020-06-17
 */
public interface ExtendBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入 仅适用于 mysql
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(List<T> entityList);

}
