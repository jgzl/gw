package cn.cleanarch.gw.gateway.feign;

import cn.cleanarch.gw.common.core.constant.ServiceNameConstants;
import cn.cleanarch.gw.common.core.model.R;
import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = ServiceNameConstants.GATEWAY_ADMIN_SERVICE)
public interface GatewayAdminFeign {
    @GetMapping("/heartbeat")
    public R<String> heartbeat();
    @PostMapping(value = "/gateway/logs")
    public R<List<GatewayLog>> saveAll(@RequestBody List<GatewayLog> list);
}
