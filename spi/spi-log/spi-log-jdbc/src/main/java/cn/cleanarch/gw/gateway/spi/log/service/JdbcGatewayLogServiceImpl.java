package cn.cleanarch.gw.gateway.spi.log.service;

import cn.cleanarch.gw.common.gateway.convert.GatewayLogConvert;
import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import cn.cleanarch.gw.common.gateway.vo.GatewayLogVo;
import cn.cleanarch.gw.gateway.spi.log.mapper.GatewayLogMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vlfac
 * @date 2021/12/22
 */
@Slf4j
@RequiredArgsConstructor
@Service("jdbcGatewayLogService")
public class JdbcGatewayLogServiceImpl implements GatewayLogService {

    private final GatewayLogMapper repository;

    @Override
    public Page<GatewayLogVo> getByGatewayRequestLog(Page<GatewayLogVo> page, GatewayLogVo gatewayRequestLog) {
        GatewayLog gatewayLog = GatewayLogConvert.INSTANCE.convertVo2Do(gatewayRequestLog);
        Page<GatewayLog> domainPage = GatewayLogConvert.INSTANCE.convertVo2Do(page);
        domainPage = repository.selectPage(domainPage, Wrappers.<GatewayLog>lambdaQuery().setEntity(gatewayLog));
        page = GatewayLogConvert.INSTANCE.convertDo2Vo(domainPage);
        return page;
    }

    @Override
    public List<GatewayLog> findAll() {
        return repository.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public List<GatewayLog> findAllById(List<String> idList) {
        return repository.selectList(Wrappers.lambdaQuery(GatewayLog.class).in(GatewayLog::getId,idList));
    }

    @Override
    public GatewayLog save(GatewayLog gatewayLog) {
        repository.insert(gatewayLog);
        return gatewayLog;
    }

    @Override
    public List<GatewayLog> saveAll(List<GatewayLog> list) {
        list.forEach(repository::insert);
        return list;
    }

    @Override
    public void deleteAll() {
        repository.delete(Wrappers.emptyWrapper());
    }

    @Override
    public void deleteAllById(List<String> idList) {
        repository.deleteBatchIds(idList);
    }
}
