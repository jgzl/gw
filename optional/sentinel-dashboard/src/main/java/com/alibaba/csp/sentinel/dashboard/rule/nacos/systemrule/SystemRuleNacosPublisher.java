package com.alibaba.csp.sentinel.dashboard.rule.nacos.systemrule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.SentinelNacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.SentinelNacosProperties;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @vlog: 高于生活，源于生活
 * @desc: 类的描述: 系统规则发布类
 * @author: vlfac
 * @createDate: 2019/12/2 19:24
 * @version: 1.0
 */
@Component("systemRuleNacosPublisher")
public class SystemRuleNacosPublisher implements DynamicRulePublisher<List<SystemRuleEntity>> {

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private SentinelNacosProperties nacosProperties;

	@Autowired
	private Converter<List<SystemRuleEntity>, String> converter;

	@Override
	public void publish(String app, List<SystemRuleEntity> rules) throws Exception {
		SentinelNacosConfigUtil.setRuleString2Nacos(
				configService,
				nacosProperties,
				app,
				SentinelNacosConfigUtil.SYSTEM_DATA_ID_POSTFIX,
				rules,
				converter
		);
	}
}
