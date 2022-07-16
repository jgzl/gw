package cn.cleanarch.gw.gateway.controller;

import cn.cleanarch.gw.common.core.annotaion.ApiVersion;
import cn.cleanarch.gw.common.core.constant.CommonConstants;
import cn.cleanarch.gw.common.core.constant.GatewayConstants;
import cn.cleanarch.gw.common.core.model.R;
import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import cn.cleanarch.gw.gateway.feign.GatewayAdminFeign;
import cn.hutool.core.thread.ThreadUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 主页
 *
 * @author li7hai26@gmail.com
 */
@Slf4j
@AllArgsConstructor
@RestController
public class IndexController {

    private final GatewayAdminFeign gatewayAdminFeign;

    /**
     * 根节点跳转
     *
     * @return
     */
    @ApiVersion("1.1.0")
    @GetMapping("/")
    public Mono<R<String>> index1() {
        return Mono.just(R.success("success,version1.1.0"));
    }

    /**
     * 根节点跳转
     *
     * @return
     */
    @ApiVersion("1.2")
    @GetMapping("/")
    public Mono<R<String>> index2() {
        return Mono.just(R.success("success,version1.2"));
    }

    /**
     * 根节点跳转
     *
     * @return
     */
    @ApiVersion("1.3")
    @GetMapping("/")
    public Mono<R<String>> index3() {
        return Mono.just(R.success("success,version1.3"));
    }

    @GetMapping(GatewayConstants.ADMIN_PREFIX+"/heartbeat")
    public Mono<R<String>> heartbeat() throws ExecutionException, InterruptedException {
        R<String> result = ThreadUtil.execAsync(gatewayAdminFeign::heartbeat).get();
        return Mono.just(result);
    }
}