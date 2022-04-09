package com.github.gw.gateway.configuration.properties;

import com.github.gw.common.core.constant.enums.RecordLogEnum;
import com.github.gw.gateway.common.GatewayConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 路径参数
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/20
 */
@Data
@ConfigurationProperties(prefix = GatewayConstants.SYSTEM_PREFIX)
public class GatewayProperties {
    private GatewayPathProperties path = new GatewayPathProperties();
    private GatewayFileProperties file = new GatewayFileProperties();
    private RecordLogEnum logType = RecordLogEnum.LocalFile;
}