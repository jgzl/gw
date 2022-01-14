package com.github.gw.gateway.admin.system.convert;

import com.github.gw.common.core.convert.BaseConvert;
import com.github.gw.gateway.admin.system.domain.SysUser;
import com.github.gw.gateway.admin.system.vo.SysUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
@Mapper
public interface UserConvert extends BaseConvert<SysUserVo, SysUser> {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);
}
