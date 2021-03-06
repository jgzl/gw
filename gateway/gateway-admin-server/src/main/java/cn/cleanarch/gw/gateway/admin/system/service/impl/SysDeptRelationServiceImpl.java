package cn.cleanarch.gw.gateway.admin.system.service.impl;

import cn.cleanarch.gw.common.model.system.domain.SysDept;
import cn.cleanarch.gw.common.model.system.domain.SysDeptRelation;
import cn.cleanarch.gw.gateway.admin.system.mapper.SysDeptRelationMapper;
import cn.cleanarch.gw.gateway.admin.system.service.SysDeptRelationService;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author li7hai26@gmail.com
 * @since 2018-02-12
 */
@Service
@AllArgsConstructor
public class SysDeptRelationServiceImpl extends ServiceImpl<SysDeptRelationMapper, SysDeptRelation>
        implements SysDeptRelationService {

    private final SysDeptRelationMapper sysDeptRelationMapper;

    /**
     * 维护部门关系
     *
     * @param sysDept 部门
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertDeptRelation(SysDept sysDept) {
        // 增加部门关系表
        List<SysDeptRelation> relationList = sysDeptRelationMapper.selectList(
                        Wrappers.<SysDeptRelation>query().lambda().eq(SysDeptRelation::getDescendant, sysDept.getParentId()))
                .stream().map(relation -> {
                    relation.setDescendant(sysDept.getDeptId());
                    return relation;
                }).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(relationList)) {
            relationList.forEach(baseMapper::insert);
        }

        // 自己也要维护到关系表中
        SysDeptRelation own = new SysDeptRelation();
        own.setDescendant(sysDept.getDeptId());
        own.setAncestor(sysDept.getDeptId());
        sysDeptRelationMapper.insert(own);
    }

    /**
     * 通过ID删除部门关系
     *
     * @param id
     */
    @Override
    public void deleteAllDeptRelation(Integer id) {
        baseMapper.deleteDeptRelationsById(id);
    }

    /**
     * 更新部门关系
     *
     * @param relation
     */
    @Override
    public void updateDeptRelation(SysDeptRelation relation) {
        baseMapper.updateDeptRelations(relation);
    }

}
