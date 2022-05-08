package com.github.jgzl.gw.gateway.admin.gateway.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.jgzl.gw.common.model.gateway.domain.GatewayRouteConf;
import com.github.jgzl.gw.common.model.gateway.vo.GatewayRouteConfVo;
import com.github.jgzl.gw.gateway.admin.gateway.service.GatewayRouteConfService;
import com.github.jgzl.gw.common.core.model.R;
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

    private final GatewayRouteConfService service;
    /**
     * 分页获取当前定义的信息
     * @return
     */
    @GetMapping("/page")
    public R<IPage<GatewayRouteConf>> pageListRoutes(Page page, GatewayRouteConfVo vo) {
        return R.success(service.page(page, new QueryWrapper<GatewayRouteConf>(vo)));
    }

    /**
     * 获取当前定义的路由信息
     *
     * @return
     */
    @GetMapping
    public R<List<GatewayRouteConf>> listRoutes() {
        return R.success(service.list());
    }

    /**
     * 新增路由
     *
     * @param vo 路由定义
     * @return
     */
    @PostMapping
    public R<Void> createRoutes(@RequestBody GatewayRouteConf vo) {
        service.saveOrUpdate(vo);
        return R.success();
    }

    /**
     * 修改路由
     *
     * @param vo 路由定义
     * @return
     */
    @PutMapping
    public R<Void> updateRoutes(@RequestBody GatewayRouteConf vo) {
        service.saveOrUpdate(vo);
        return R.success();
    }

    /**
     * 删除路由
     *
     * @param routeId 路由id
     * @return
     */
    @DeleteMapping("/{routeId}")
    public R<Void> deleteRoutes(@PathVariable String routeId) {
        service.deleteRoute(routeId);
        return R.success();
    }

}
