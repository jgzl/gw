package com.github.jgzl.gw.gateway.controller;

import com.github.jgzl.gw.common.core.annotaion.ApiVersion;
import com.github.jgzl.gw.common.core.model.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 主页
 *
 * @author li7hai26@gmail.com
 */
@Slf4j
@AllArgsConstructor
@RestController
public class IndexController {

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
}