package com.github.gw.gateway.admin.gateway.controller;

import com.github.gw.common.core.domain.R;
import com.github.gw.gateway.admin.gateway.domain.GatewayAccessConf;
import com.github.gw.gateway.admin.gateway.service.GatewayAccessConfService;
import com.github.gw.gateway.admin.gateway.vo.GatewayAccessConfVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 网关管理-网关访问模块
 *
 * @author li7hai26@gmail.com
 * @date 2018-11-06 10:17:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/gateway/access")
public class GatewayAccessConfController {

    private final GatewayAccessConfService service;

    /**
     * 分页获取当前定义的信息
     *
     * @return
     */
    @GetMapping
    public R<List<GatewayAccessConf>> listRoutes() {
        return R.ok(service.list());
    }

    /**
     * 新增
     *
     * @param vo 定义
     * @return
     */
    @PostMapping
    public R<Void> createItem(@RequestBody GatewayAccessConf vo) {
        service.saveOrUpdate(vo);
        return R.ok();
    }

    /**
     * 修改
     *
     * @param vo 定义
     * @return
     */
    @PutMapping
    public R<Void> updateItem(@RequestBody GatewayAccessConf vo) {
        service.saveOrUpdate(vo);
        return R.ok();
    }

    /**
     * 删除
     *
     * @param id 路由id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Void> deleteItem(@PathVariable String id) {
        service.deleteItem(id);
        return R.ok();
    }

    /**
     * 更新状态-禁用状态
     *
     * @param vo 用户信息
     * @return R
     */
    @PutMapping("/status")
    public R<Boolean> updateUserForLockFlag(@Valid @RequestBody GatewayAccessConfVo vo) {
        return R.ok(service.updateStatus(vo));
    }
}
