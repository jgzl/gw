package com.github.gw.gateway.controller;

import com.github.gw.common.core.domain.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

/**
 * 主页
 * @author li7hai26@gmail.com
 */
@Slf4j
@AllArgsConstructor
@Controller
public class IndexController {

    /**
     * 根节点跳转
     *
     * @return
     */
    @GetMapping("/")
    @ResponseBody
    public Mono<R<String>> index() {
        return Mono.just(R.ok("success"));
    }
}