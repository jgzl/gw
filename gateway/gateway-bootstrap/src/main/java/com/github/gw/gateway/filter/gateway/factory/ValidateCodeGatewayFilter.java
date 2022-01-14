package com.github.gw.gateway.filter.gateway.factory;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gw.common.core.constant.CommonConstants;
import com.github.gw.common.core.domain.R;
import com.github.gw.common.core.exception.CaptchaException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 网关可选过滤器-登录逻辑验证码处理
 *
 * @author li7hai26@gmail.com
 * @date 2021/5/19
 */
@Slf4j
@Component
@AllArgsConstructor
public class ValidateCodeGatewayFilter extends AbstractGatewayFilterFactory {

    private final ObjectMapper objectMapper;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是账号密码登录直接向下执行
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), "/gateway-admin/login/form")) {
                return chain.filter(exchange);
            }

            try {
                // 校验验证码
                checkCode(request);
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                response.setStatusCode(HttpStatus.PRECONDITION_REQUIRED);
                try {
                    return response.writeWith(Mono.just(
                            response.bufferFactory().wrap(objectMapper.writeValueAsBytes(R.fail(e.getMessage())))));
                } catch (JsonProcessingException e1) {
                    log.error("对象输出异常", e1);
                }
            }
            return chain.filter(exchange);
        };
    }

    /**
     * 检查code
     *
     * @param request
     */
    @SneakyThrows
    private void checkCode(ServerHttpRequest request) {
        String code = request.getQueryParams().getFirst("code");

        if (StrUtil.isBlank(code)) {
            throw new CaptchaException("验证码不能为空");
        }

        String randomStr = request.getQueryParams().getFirst("randomStr");

        // 若是滑块登录
        if (CommonConstants.IMAGE_CODE_TYPE.equalsIgnoreCase(randomStr)) {
            CaptchaService captchaService = SpringUtil.getBean(CaptchaService.class);
            CaptchaVO vo = new CaptchaVO();
            vo.setCaptchaVerification(code);
            vo.setCaptchaType(CommonConstants.IMAGE_CODE_TYPE);
            if (!captchaService.verification(vo).isSuccess()) {
                throw new CaptchaException("验证码不能为空");
            }
        }
    }

}
