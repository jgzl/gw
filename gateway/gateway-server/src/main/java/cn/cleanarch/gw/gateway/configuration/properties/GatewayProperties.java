package cn.cleanarch.gw.gateway.configuration.properties;

import cn.cleanarch.gw.common.core.constant.GatewayConstants;
import cn.cleanarch.gw.common.core.constant.enums.RecordLogEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 网关配置
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/20
 */
@Data
@ConfigurationProperties(prefix = GatewayConstants.CONFIGURATION_PREFIX)
public class GatewayProperties {
    private String apiPrefix = GatewayConstants.API_PREFIX;
    private GatewayPathProperties path = new GatewayPathProperties();
    private GatewayFileProperties file = new GatewayFileProperties();
    private RecordLogEnum logType = RecordLogEnum.localfile;
}