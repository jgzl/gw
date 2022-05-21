package cn.cleanarch.gw.tool.infra.mapper.dao;

import cn.cleanarch.gw.tool.infra.mapper.dataobject.GatewayAccessConfDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 整洁架构
 * @version 1.0
 * @title: GatewayAccessConfDORepository
 * @date: 2022/5/21 21:16
 */
public interface GatewayAccessConfDORepository extends JpaRepository<GatewayAccessConfDO, Long> {

}
