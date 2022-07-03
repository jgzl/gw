package cn.cleanarch.gw.gateway.spi.log.mapper;

import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网关访问
 *
 * @author vlfac
 * @date 2018-11-06 10:17:18
 */
@Mapper
public interface GatewayLogMapper extends BaseMapper<GatewayLog> {

}
