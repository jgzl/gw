package com.github.gw.gateway.admin.system.controller;

import com.github.gw.common.core.domain.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/")
    public R<String> index() {
        return R.ok("success");
    }

}