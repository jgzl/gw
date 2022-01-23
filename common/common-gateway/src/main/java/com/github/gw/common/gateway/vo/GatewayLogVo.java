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
     * 创建时间
     */
    private String[] createTimeRange;

    /**
     * 更新时间
     */
    private String[] updateTimeRange;
}
