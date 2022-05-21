package cn.cleanarch.gw.gateway.admin.gateway.service.impl;

import cn.cleanarch.gw.common.core.constant.enums.StatusEnum;
import cn.cleanarch.gw.common.model.gateway.convert.AccessConvert;
import cn.cleanarch.gw.common.model.gateway.domain.GatewayAccessConf;
import cn.cleanarch.gw.common.model.gateway.vo.GatewayAccessConfVo;
import cn.cleanarch.gw.common.test.core.ut.BaseDbAndRedisUnitTest;
import cn.cleanarch.gw.common.test.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import({GatewayAccessConfServiceImpl.class})
public class GatewayAccessConfServiceImplTest extends BaseDbAndRedisUnitTest {

    @Resource
    private GatewayAccessConfServiceImpl gatewayAccessConfService;

    @Test
    public void test_saveOrUpdate_success() {
        GatewayAccessConf saveDO = RandomUtil.randomPojo(GatewayAccessConf.class, conf -> {
            conf.setStatus(StatusEnum.ENABLE.getCode());
        });
        gatewayAccessConfService.saveOrUpdate(saveDO);
        GatewayAccessConf findDO = gatewayAccessConfService.getById(saveDO.getId());
        assertNotNull(findDO);
    }

    @Test
    public void test_updateStatus_success() {
        GatewayAccessConf saveDO = RandomUtil.randomPojo(GatewayAccessConf.class, conf -> {
            conf.setStatus(StatusEnum.ENABLE.getCode());
        });
        gatewayAccessConfService.saveOrUpdate(saveDO);
        GatewayAccessConfVo saveVO = AccessConvert.INSTANCE.convertDo2Vo(saveDO);
        gatewayAccessConfService.updateStatus(saveVO);
        GatewayAccessConf findDO = gatewayAccessConfService.getById(saveDO.getId());
        assertNotNull(findDO);
    }
}