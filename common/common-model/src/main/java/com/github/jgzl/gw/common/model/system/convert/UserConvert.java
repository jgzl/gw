package com.github.jgzl.gw.common.model.system.convert;

import com.github.jgzl.gw.common.model.base.BaseConvert;
import com.github.jgzl.gw.common.model.system.vo.SysUserVo;
import com.github.jgzl.gw.common.model.system.domain.SysUser;
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
