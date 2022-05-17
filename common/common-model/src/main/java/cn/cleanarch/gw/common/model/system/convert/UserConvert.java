package cn.cleanarch.gw.common.model.system.convert;

import cn.cleanarch.gw.common.model.base.BaseConvert;
import cn.cleanarch.gw.common.model.system.domain.SysUser;
import cn.cleanarch.gw.common.model.system.vo.SysUserVo;
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
