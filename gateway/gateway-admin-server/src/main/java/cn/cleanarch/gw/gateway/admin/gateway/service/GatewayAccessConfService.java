package cn.cleanarch.gw.gateway.admin.gateway.service;

import cn.cleanarch.gw.common.model.gateway.domain.GatewayAccessConf;
import cn.cleanarch.gw.common.model.gateway.vo.GatewayAccessConfVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 路由
 *
 * @author li7hai26@gmail.com
 * @date 2018-11-06 10:17:18
 */
public interface GatewayAccessConfService extends IService<GatewayAccessConf> {

    /**
     * 删除路由信息
     *
     * @param id 路由id
     * @return
     */
	void deleteItem(String id);

    Boolean updateStatus(GatewayAccessConfVo vo);
}
