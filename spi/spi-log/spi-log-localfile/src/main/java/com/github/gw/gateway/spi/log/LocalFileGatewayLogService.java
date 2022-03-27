package com.github.gw.gateway.spi.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.gw.common.gateway.domain.GatewayLog;
import com.github.gw.common.gateway.vo.GatewayLogVo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/22
 */
@Slf4j
@Service("localFileGatewayLogService")
public class LocalFileGatewayLogService implements GatewayLogService {

    @Override
    public Page<GatewayLogVo> getByGatewayRequestLog(Page<GatewayLogVo> page,GatewayLogVo gatewayRequestLog) {
        List<GatewayLogVo> result = Lists.newArrayList();
        page.setRecords(result);
        page.setTotal(10);
        return page;
    }

    @Override
    public Iterable<GatewayLog> findAll() {
        return Lists.newArrayList();
    }

    @Override
    public Iterable<GatewayLog> findAllById(List<String> idList) {
        return Lists.newArrayList();
    }

    @Override
    public Iterable<GatewayLog> saveAll(List<GatewayLog> list) {
        return list;
    }

    @Override
    public void deleteAll() {
        log.info("删除数据");
    }

    @Override
    public void deleteAllById(List<String> idList) {
        log.info("删除数据");
    }
}
