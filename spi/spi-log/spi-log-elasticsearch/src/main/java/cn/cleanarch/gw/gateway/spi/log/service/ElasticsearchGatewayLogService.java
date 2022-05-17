package cn.cleanarch.gw.gateway.spi.log.service;

import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import cn.cleanarch.gw.common.gateway.vo.GatewayLogVo;
import cn.cleanarch.gw.gateway.spi.log.repository.GatewayLogElasticsearchRepository;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@Slf4j
@RequiredArgsConstructor
@Service("elasticsearchGatewayLogService")
public class ElasticsearchGatewayLogService implements GatewayLogService {

    private final GatewayLogElasticsearchRepository repository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public Page<GatewayLogVo> getByGatewayRequestLog(Page<GatewayLogVo> page,GatewayLogVo gatewayRequestLog) {

        String environment = gatewayRequestLog.getEnvironment();
        String apiKey = gatewayRequestLog.getApiKey();
        String apiSecret = gatewayRequestLog.getApiSecret();
        String system = gatewayRequestLog.getSystem();
        String targetService = gatewayRequestLog.getTargetService();
        String requestSourceIp = gatewayRequestLog.getRequestSourceIp();
        String requestPath = gatewayRequestLog.getRequestPath();
        String requestPathAndQuery = gatewayRequestLog.getRequestPathAndQuery();
        String requestMethod = gatewayRequestLog.getRequestMethod();
        String requestBody = gatewayRequestLog.getRequestBody();
        String responseBody = gatewayRequestLog.getResponseBody();
        String httpStatus = gatewayRequestLog.getHttpStatus();
        String errorMsg = gatewayRequestLog.getErrorMsg();
        String[] createTimeRange = gatewayRequestLog.getCreateTimeRange();
        String[] updateTimeRange = gatewayRequestLog.getUpdateTimeRange();

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
        if (StrUtil.isNotBlank(targetService)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("targetService", targetService));
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

        if (ArrayUtil.isNotEmpty(createTimeRange)) {
            LocalDateTime fromCreateTime = DateUtil.parseLocalDateTime(createTimeRange[0], DatePattern.NORM_DATETIME_PATTERN);
            LocalDateTime toCreateTime = DateUtil.parseLocalDateTime(createTimeRange[1], DatePattern.NORM_DATETIME_PATTERN);
            boolQueryBuilder.must(QueryBuilders.rangeQuery("createTime").from(fromCreateTime).to(toCreateTime));
        }
        if (ArrayUtil.isNotEmpty(updateTimeRange)) {
            LocalDateTime fromUpdateTime = DateUtil.parseLocalDateTime(updateTimeRange[0], DatePattern.NORM_DATETIME_PATTERN);
            LocalDateTime toUpdateTime = DateUtil.parseLocalDateTime(updateTimeRange[1], DatePattern.NORM_DATETIME_PATTERN);
            boolQueryBuilder.must(QueryBuilders.rangeQuery("updateTime").from(fromUpdateTime).to(toUpdateTime));
        }
        if (page.getCurrent()>Integer.MAX_VALUE) {
            throw new RuntimeException("current过大,不允许分页查询");
        }
        Pageable pageable = PageRequest.of((int) page.getCurrent() -1, (int) page.getSize());
        nativeSearchQuery.setPageable(pageable);
        SearchHits<GatewayLogVo> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, GatewayLogVo.class);
        long totalHits = searchHits.getTotalHits();
        List<GatewayLogVo> result = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        page.setRecords(result);
        page.setTotal(totalHits);
        return page;
    }

    @Override
    public Iterable<GatewayLog> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<GatewayLog> findAllById(List<String> idList) {
        return repository.findAllById(idList);
    }

    @Override
    public Iterable<GatewayLog> saveAll(List<GatewayLog> list) {
        return repository.saveAll(list);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void deleteAllById(List<String> idList) {
        repository.deleteAllById(idList);
    }
}
