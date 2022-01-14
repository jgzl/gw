package com.github.gw.gateway.admin.gateway.controller;

import com.github.gw.common.core.domain.R;
import com.github.gw.gateway.admin.gateway.domain.GatewayRouteConf;
import com.github.gw.gateway.admin.gateway.service.GatewayRouteConfService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 网关管理-网关路由模块
 *
 * @author li7hai26@gmail.com
 * @date 2018-11-06 10:17:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/gateway/route")
public class GatewayRouteConfController {

    private final GatewayRouteConfService gatewayRouteConfService;

    /**
     * 获取当前定义的路由信息
     *
     * @return
     */
    @GetMapping
    public R<List<GatewayRouteConf>> listRoutes() {
        return R.ok(gatewayRouteConfService.list());
    }

    /**
     * 新增路由
     *
     * @param vo 路由定义
     * @return
     */
    @PostMapping
    public R<Void> createRoutes(@RequestBody GatewayRouteConf vo) {
        gatewayRouteConfService.saveOrUpdate(vo);
        return R.ok();
    }

    /**
     * 修改路由
     *
     * @param vo 路由定义
     * @return
     */
    @PutMapping
    public R<Void> updateRoutes(@RequestBody GatewayRouteConf vo) {
        gatewayRouteConfService.saveOrUpdate(vo);
        return R.ok();
    }

    /**
     * 删除路由
     *
     * @param routeId 路由id
     * @return
     */
    @DeleteMapping("/{routeId}")
    public R<Void> deleteRoutes(@PathVariable String routeId) {
        gatewayRouteConfService.deleteRoute(routeId);
        return R.ok();
    }

}
