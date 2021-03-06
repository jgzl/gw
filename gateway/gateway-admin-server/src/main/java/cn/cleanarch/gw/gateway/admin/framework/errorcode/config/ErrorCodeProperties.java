package cn.cleanarch.gw.gateway.admin.framework.errorcode.config;

import cn.cleanarch.gw.common.core.constant.CacheConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 错误码的配置属性类
 *
 * @author lihaifeng
 */
@ConfigurationProperties(CacheConstants.CONFIGURATION_PREFIX +".error-code")
@Data
@Validated
public class ErrorCodeProperties {

    /**
     * 错误码枚举类
     */
    @NotNull(message = "错误码枚举类不能为空")
    private List<String> constantsClassList;

}
