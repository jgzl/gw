package com.alibaba.csp.sentinel.dashboard.rule.nacos.systemrule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.SentinelNacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.SentinelNacosProperties;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @vlog: 高于生活，源于生活
 * @desc: 类的描述:系统规则提供类
 * @author: vlfac
 * @createDate: 2019/12/2 19:17
 * @version: 1.0
 */
@Component("systemRuleNacosProvider")
public class SystemRuleNacosProvider implements DynamicRuleProvider<List<SystemRuleEntity>> {

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private SentinelNacosProperties nacosProperties;

	@Autowired
	private Converter<String, List<SystemRuleEntity>> converter;

	@Override
	public List<SystemRuleEntity> getRules(String appName) throws Exception {
		return SentinelNacosConfigUtil.getRuleEntities4Nacos(
				configService,
				nacosProperties,
				appName,
				SentinelNacosConfigUtil.SYSTEM_DATA_ID_POSTFIX,
				SystemRuleEntity.class,
				converter
		);
	}
}
