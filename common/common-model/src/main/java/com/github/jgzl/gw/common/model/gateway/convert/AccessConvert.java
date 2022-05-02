package com.github.jgzl.gw.common.model.gateway.convert;

import com.github.jgzl.gw.common.model.base.BaseConvert;
import com.github.jgzl.gw.common.model.gateway.domain.GatewayAccessConf;
import com.github.jgzl.gw.common.model.gateway.vo.GatewayAccessConfVo;
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
