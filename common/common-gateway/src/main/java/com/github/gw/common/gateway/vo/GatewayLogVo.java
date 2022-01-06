package com.github.gw.common.gateway.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.gw.common.gateway.domain.GatewayLog;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 网关日志Vo
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/16
 */
@Data
public class GatewayLogVo extends GatewayLog {

    /**
     * 起始创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime fromCreateTime;
    /**
     * 结束创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime toCreateTime;
    /**
     * 起始更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime fromUpdateTime;
    /**
     * 结束更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime toUpdateTime;
}
