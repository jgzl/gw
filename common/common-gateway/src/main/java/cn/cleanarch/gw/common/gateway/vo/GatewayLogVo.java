package cn.cleanarch.gw.common.gateway.vo;

import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import lombok.Data;

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
