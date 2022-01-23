package com.github.gw.common.gateway.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;

/**
 * 网关日志
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/16
 */
@Data
@Setting
@Document(indexName = "gateway_log")
public class GatewayLog {

    /**
     * 主键ID
     */
    @Id
    private String id;

    /**
     * 请求来源系统
     */
    private String system;

    /**
     * 请求来源key
     */
    private String apiKey;

    /**
     * 请求来源secret
     */
    private String apiSecret;

    /**
     * 请求来源环境
     */
    private String environment;

    /**
     * 请求路径
     */
    private String requestPath;

    /**
     * 请求路径参数
     */
    private String requestPathAndQuery;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求头
     */
    @Field(type = FieldType.Text)
    private String requestHeader;

    /**
     * 请求源IP
     */
    private String requestSourceIp;

    /**
     * 请求参数值
     */
    @Field(type = FieldType.Text)
    private String requestBody;

    /**
     * 返回参数值
     */
    @Field(type = FieldType.Text)
    private String responseBody;

    /**
     * 请求时长ms
     */
    private Long executeTime;

    /**
     * 请求返回HTTP状态码
     */
    private String httpStatus;

    /**
     * 请求错误信息
     */
    @Field(type = FieldType.Text)
    private String errorMsg;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}