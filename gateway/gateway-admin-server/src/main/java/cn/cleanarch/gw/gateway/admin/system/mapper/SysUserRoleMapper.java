package cn.cleanarch.gw.gateway.admin.system.mapper;

import cn.cleanarch.gw.common.data.mapper.ExtendBaseMapper;
import cn.cleanarch.gw.common.model.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author li7hai26@gmail.com
 * @since 2017-10-29
 */
@Mapper
public interface SysUserRoleMapper extends ExtendBaseMapper<SysUserRole> {

    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户ID
     * @return boolean
     * @author 寻欢·李
     * @date 2017年12月7日 16:31:38
     */
    Boolean deleteByUserId(@Param("userId") Long userId);

}
