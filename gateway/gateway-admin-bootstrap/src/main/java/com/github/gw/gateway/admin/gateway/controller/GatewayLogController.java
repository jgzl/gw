package com.github.gw.gateway.admin.gateway.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.github.gw.common.core.domain.R;
import com.github.gw.common.gateway.domain.GatewayLog;
import com.github.gw.common.gateway.vo.GatewayLogVo;
import com.github.gw.gateway.admin.gateway.repository.GatewayLogElasticsearchRepository;
import com.github.gw.gateway.admin.gateway.service.IGatewayLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 网关日志管理模块
 * @author li7hai26@gmail.com
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/gateway")
public class GatewayLogController {

    private final GatewayLogElasticsearchRepository repository;

    private final IGatewayLogService service;

    /**
     * 获取所有网关日志
     *
     * @return
     */
    @GetMapping("/logs")
    public R<Iterable<GatewayLog>> findAll() {
        Iterable<GatewayLog> result = repository.findAll();
        return R.ok(result);
    }

    /**
     * 根据ID集合获取网关日志
     * @param ids id集合字符串
     * @return
     */
    @GetMapping("/logs/{ids}")
    public R<Iterable<GatewayLog>> findAllById(@PathVariable String ids) {
        if (StrUtil.isBlank(ids)) {
            return R.ok();
        }
        List<String> idList = Arrays.stream(ids.split(",")).collect(Collectors.toList());
        Iterable<GatewayLog> result = repository.findAllById(idList);
        return R.ok(result);
    }

    /**
     * 批量保存网关日志
     *
     * @param list 网关请求对象集合
     * @return
     */
    @PostMapping("/logs")
    public R<Iterable<GatewayLog>> saveAll(@RequestBody List<GatewayLog> list) {
        if (CollUtil.isEmpty(list)) {
            list = ListUtil.toList();
        }
        for (GatewayLog gatewayLog : list) {
            gatewayLog.setId(IdUtil.fastUUID());
        }
        Iterable<GatewayLog> result = repository.saveAll(list);
        return R.ok(result);
    }

    /**
     * 删除所有网关日志
     *
     * @return
     */
    @DeleteMapping("/logs")
    public R<Void> deleteAll() {
        repository.deleteAll();
        return R.ok();
    }

    /**
     * 根据ID集合删除网关日志
     *
     * @param ids id集合字符串
     * @return
     */
    @DeleteMapping("/logs/{ids}")
    public R<Void> deleteAllByIds(@PathVariable String ids) {
        if (StrUtil.isBlank(ids)) {
            return R.ok();
        }
        List<String> idList = Arrays.stream(ids.split(",")).collect(Collectors.toList());
        repository.deleteAllById(idList);
        return R.ok();
    }

    /**
     * 根据参数从数据仓库中获取网关日志
     * @param gatewayRequestLog 网关请求对象
     * @return
     */
    @GetMapping("/logs/search")
    public R<List<GatewayLog>> search(GatewayLogVo gatewayRequestLog) {
        List<GatewayLog> result = service.getByGatewayRequestLog(gatewayRequestLog);
        return R.ok(result);
    }
}