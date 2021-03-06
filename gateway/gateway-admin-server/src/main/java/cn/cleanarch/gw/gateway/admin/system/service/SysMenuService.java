package cn.cleanarch.gw.gateway.admin.system.service;

import cn.cleanarch.gw.common.core.model.R;
import cn.cleanarch.gw.common.model.system.domain.SysMenu;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author li7hai26@gmail.com
 * @since 2017-10-29
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 通过角色编号查询URL 权限
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<SysMenu> findMenuByRoleId(Long roleId);

    /**
     * 级联删除菜单
     *
     * @param id 菜单ID
     * @return 成功、失败
     */
    R removeMenuById(Integer id);

    /**
     * 更新菜单信息
     *
     * @param sysMenu 菜单信息
     * @return 成功、失败
     */
    Boolean updateMenuById(SysMenu sysMenu);

    /**
     * 构建树
     *
     * @param lazy     是否是懒加载
     * @param parentId 父节点ID
     * @return
     */
    List<Tree<Long>> treeMenu(boolean lazy, Long parentId);

    /**
     * 查询菜单
     *
     * @param voSet
     * @param parentId
     * @return
     */
    List<Tree<Long>> filterMenu(Set<SysMenu> voSet, String type, Long parentId);

}
