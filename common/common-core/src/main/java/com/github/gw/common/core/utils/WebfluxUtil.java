package com.github.gw.common.core.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.gw.common.core.constant.Constants;
import com.github.gw.common.core.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * 客户端工具类
 *
 * @author li7hai26@gmail.com
 */
@Slf4j
public class WebfluxUtil {

    /**
     * 从请求头或者请求路径中获取参数(优先获取请求头中的参数,请求头权重更大)
     */
    public static String getParameterByHeaderOrPath(ServerHttpRequest request, String name) {
        String value = getHeader(request, name);
        if (StrUtil.isBlank(value)) {
            value = getParameter(request, name);
        }
        return value;
    }

    /**
     * 获取String参数
     */
    public static String getParameter(ServerHttpRequest request, String name) {
        return request.getQueryParams().getFirst(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(ServerHttpRequest request, String name, String defaultValue) {
        return Convert.toStr(request.getQueryParams().getFirst(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(ServerHttpRequest request, String name) {
        return Convert.toInt(request.getQueryParams().getFirst(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(ServerHttpRequest request, String name, Integer defaultValue) {
        return Convert.toInt(request.getQueryParams().getFirst(name), defaultValue);
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(ServerHttpRequest request, String name) {
        return Convert.toBool(request.getQueryParams().getFirst(name));
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(ServerHttpRequest request, String name, Boolean defaultValue) {
        return Convert.toBool(request.getQueryParams().getFirst(name), defaultValue);
    }

    public static String getHeader(ServerHttpRequest request, String name) {
        String value = request.getHeaders().getFirst(name);
        if (StringUtils.isEmpty(value)) {
            return StringUtils.EMPTY;
        }
        return urlDecode(value);
    }

    public static Map<String, String> getHeaders(ServerHttpRequest request) {
        return request.getHeaders().toSingleValueMap();
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        response.setStatus(org.springframework.http.HttpStatus.OK.value());
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            response.getWriter().print(string);
        } catch (IOException e) {
            log.error("生成文件流失败:", e);
        }
        return null;
    }

    /**
     * 内容解码
     *
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value) {
        return webFluxResponseWriter(response, HttpStatus.OK, value, R.FAIL);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param code     响应状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value, int code) {
        return webFluxResponseWriter(response, HttpStatus.OK, value, code);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param status   http状态码
     * @param code     响应状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, Object value, int code) {
        return webFluxResponseWriter(response, MediaType.APPLICATION_JSON_VALUE, status, value, code);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response    ServerHttpResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param code        响应状态码
     * @param value       响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, String contentType, HttpStatus status, Object value, int code) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);
        R<?> result = R.fail(code, value.toString());
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(result).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
