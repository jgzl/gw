package com.github.gw.gateway.admin.gateway.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.gw.common.gateway.domain.GatewayLog;
import com.github.gw.common.gateway.vo.GatewayLogVo;
import com.github.gw.gateway.admin.gateway.service.IGatewayLogService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/22
 */
@Service
@RequiredArgsConstructor
public class GatewayLogService implements IGatewayLogService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<GatewayLog> getByGatewayRequestLog(GatewayLogVo gatewayRequestLog) {

        String environment = gatewayRequestLog.getEnvironment();
        String apiKey = gatewayRequestLog.getApiKey();
        String apiSecret = gatewayRequestLog.getApiSecret();
        String system = gatewayRequestLog.getSystem();
        String requestSourceIp = gatewayRequestLog.getRequestSourceIp();
        String requestPath = gatewayRequestLog.getRequestPath();
        String requestPathAndQuery = gatewayRequestLog.getRequestPathAndQuery();
        String requestMethod = gatewayRequestLog.getRequestMethod();
        String requestBody = gatewayRequestLog.getRequestBody();
        String responseBody = gatewayRequestLog.getResponseBody();
        String httpStatus = gatewayRequestLog.getHttpStatus();
        String errorMsg = gatewayRequestLog.getErrorMsg();
        LocalDateTime fromCreateTime = gatewayRequestLog.getFromCreateTime();
        LocalDateTime toCreateTime = gatewayRequestLog.getToCreateTime();
        LocalDateTime fromUpdateTime = gatewayRequestLog.getFromUpdateTime();
        LocalDateTime toUpdateTime = gatewayRequestLog.getToUpdateTime();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(boolQueryBuilder);
        if (StrUtil.isNotBlank(environment)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("environment", environment));
        }
        if (StrUtil.isNotBlank(apiKey)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("apiKey", apiKey));
        }
        if (StrUtil.isNotBlank(apiSecret)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("apiSecret", apiSecret));
        }
        if (StrUtil.isNotBlank(system)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("system", system));
        }
        if (StrUtil.isNotBlank(requestSourceIp)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("requestSourceIp", requestSourceIp));
        }
        if (StrUtil.isNotBlank(requestPath)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("requestPath", requestPath));
        }
        if (StrUtil.isNotBlank(requestPathAndQuery)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("requestPathAndQuery", requestPathAndQuery));
        }
        if (StrUtil.isNotBlank(requestMethod)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("requestMethod", requestMethod));
        }
        if (StrUtil.isNotBlank(requestBody)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("requestBody", requestBody));
        }
        if (StrUtil.isNotBlank(responseBody)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("responseBody", responseBody));
        }
        if (StrUtil.isNotBlank(httpStatus)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("httpStatus", httpStatus));
        }
        if (StrUtil.isNotBlank(errorMsg)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("errorMsg", errorMsg));
        }
        if (fromCreateTime!=null&&toCreateTime!=null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("createTime").from(fromCreateTime).to(toCreateTime));
        }
        if (fromUpdateTime!=null&&toUpdateTime!=null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("updateTime").from(fromUpdateTime).to(toUpdateTime));
        }
        SearchHits<GatewayLog> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, GatewayLog.class);
        List<GatewayLog> result = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return result;
    }
}
