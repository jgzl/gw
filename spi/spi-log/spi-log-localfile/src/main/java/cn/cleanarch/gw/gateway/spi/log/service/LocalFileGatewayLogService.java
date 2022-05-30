package cn.cleanarch.gw.gateway.spi.log.service;

import cn.cleanarch.gw.common.core.constant.ServiceNameConstants;
import cn.cleanarch.gw.common.core.utils.JacksonUtil;
import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import cn.cleanarch.gw.common.gateway.vo.GatewayLogVo;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/22
 */
@Slf4j
@RequiredArgsConstructor
@Service("localFileGatewayLogService")
public class LocalFileGatewayLogService implements GatewayLogService {

    private final Environment environment;

    @Override
    public Page<GatewayLogVo> getByGatewayRequestLog(Page<GatewayLogVo> page, GatewayLogVo gatewayRequestLog) {
        String prefixPath = environment.getProperty("logging.file.prefix");
        Reader reader = FileUtil.getUtf8Reader(prefixPath + "/" + ServiceNameConstants.GATEWAY_SERVICE + "/gateway.log");
        List<String> lineList = ListUtil.toList();
        IoUtil.readLines(reader, lineList);
        List<GatewayLogVo> result = lineList.stream().map(line -> JacksonUtil.parseObject(line, GatewayLogVo.class)).skip((page.getCurrent() - 1) * page.getSize()).limit(page.getSize()).collect(Collectors.toList());
        page.setRecords(result);
        page.setTotal(lineList.size());
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
