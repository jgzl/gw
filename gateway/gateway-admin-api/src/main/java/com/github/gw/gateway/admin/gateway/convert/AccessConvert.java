package com.github.gw.gateway.admin.gateway.convert;

import com.github.gw.common.core.convert.BaseConvert;
import com.github.gw.gateway.admin.gateway.domain.GatewayAccessConf;
import com.github.gw.gateway.admin.gateway.vo.GatewayAccessConfVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
@Mapper
public interface AccessConvert extends BaseConvert<GatewayAccessConfVo, GatewayAccessConf> {
    AccessConvert INSTANCE = Mappers.getMapper(AccessConvert.class);
}
