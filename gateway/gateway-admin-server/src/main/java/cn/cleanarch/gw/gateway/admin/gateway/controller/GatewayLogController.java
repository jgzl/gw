package cn.cleanarch.gw.gateway.admin.gateway.controller;

import cn.cleanarch.gw.common.core.model.R;
import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import cn.cleanarch.gw.common.gateway.vo.GatewayLogVo;
import cn.cleanarch.gw.gateway.spi.log.service.GatewayLogService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 网关管理-网关日志模块
 *
 * @author li7hai26@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/gateway/logs")
public class GatewayLogController {

    @Autowired
    private GatewayLogService service;

    /**
     * 获取所有网关日志
     *
     * @return
     */
    @GetMapping
    public R<List<GatewayLog>> findAll() {
        List<GatewayLog> result = service.findAll();
        return R.success(result);
    }

    /**
     * 根据ID集合获取网关日志
     *
     * @param ids id集合字符串
     * @return
     */
    @GetMapping("/{ids}")
    public R<List<GatewayLog>> findAllById(@PathVariable String ids) {
        if (StrUtil.isBlank(ids)) {
            return R.success();
        }
        List<String> idList = Arrays.stream(ids.split(",")).collect(Collectors.toList());
        List<GatewayLog> result = service.findAllById(idList);
        return R.success(result);
    }

    /**
     * 批量保存网关日志
     *
     * @param list 网关请求对象集合
     * @return
     */
    @PostMapping
    public R<List<GatewayLog>> saveAll(@RequestBody List<GatewayLog> list) {
        if (CollUtil.isEmpty(list)) {
            list = ListUtil.toList();
        }
        for (GatewayLog gatewayLog : list) {
            gatewayLog.setId(IdUtil.fastUUID());
        }
        List<GatewayLog> result = service.saveAll(list);
        return R.success(result);
    }

    /**
     * 删除所有网关日志
     *
     * @return
     */
    @DeleteMapping
    public R<Void> deleteAll() {
        service.deleteAll();
        return R.success();
    }

    /**
     * 根据ID集合删除网关日志
     *
     * @param ids id集合字符串
     * @return
     */
    @DeleteMapping("/{ids}")
    public R<Void> deleteAllByIds(@PathVariable String ids) {
        if (StrUtil.isBlank(ids)) {
            return R.success();
        }
        List<String> idList = Arrays.stream(ids.split(",")).collect(Collectors.toList());
        service.deleteAllById(idList);
        return R.success();
    }

    /**
     * 根据参数从数据仓库中获取网关日志
     * @param page 分页参数
     * @param gatewayRequestLog 网关请求对象
     * @return
     */
    @GetMapping("/search")
    public R<IPage<GatewayLogVo>> search(Page<GatewayLogVo> page, GatewayLogVo gatewayRequestLog) {
        Page<GatewayLogVo> result = service.getByGatewayRequestLog(page,gatewayRequestLog);
        return R.success(result);
    }
}