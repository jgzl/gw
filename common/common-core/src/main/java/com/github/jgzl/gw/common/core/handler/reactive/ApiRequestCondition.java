package com.github.jgzl.gw.common.core.handler.reactive;

import com.github.jgzl.gw.common.core.constant.HttpHeaderConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.reactive.result.condition.RequestCondition;
import org.springframework.web.server.ServerWebExchange;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiRequestCondition implements RequestCondition<ApiRequestCondition> {

    private int apiVersion;

    public ApiRequestCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }
    public int getApiVersion() {
        return apiVersion;
    }

    @Override
    public ApiRequestCondition combine(ApiRequestCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiRequestCondition(other.getApiVersion());
    }

    @Override
    public int compareTo(ApiRequestCondition other, ServerWebExchange request) {
        //对符合请求版本的版本号进行排序
        return other.getApiVersion() - this.apiVersion;
    }

    @Override
    public ApiRequestCondition getMatchingCondition(ServerWebExchange request) {
        //设置默认版本号，请求版本号错误时使用最新版本号的接口
        int version=10000;
        //得到请求版本号
        String apiVersion=request.getRequest().getHeaders().getFirst(HttpHeaderConstants.X_BUSINESS_API_VERSION);
        if(StringUtils.isNotEmpty(apiVersion)){
            Matcher m = Pattern.compile("v(\\d+)").matcher(apiVersion);
            if (m.find()) {
                version = Integer.parseInt(m.group(1));
            }
        }
        // 返回小于等于请求版本号的版本
        if (version >= this.apiVersion){
            return this;
        }
        return null;
    }
}
