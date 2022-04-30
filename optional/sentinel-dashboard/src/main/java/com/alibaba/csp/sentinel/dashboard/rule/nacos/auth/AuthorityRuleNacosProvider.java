package com.alibaba.csp.sentinel.dashboard.rule.nacos.auth;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
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
 * @desc: 类的描述:基于Nacos配置中心持久化
 * @author: vlfac
 * @createDate: 2019/12/2 14:31
 * @version: 1.0
 */
@Component("authorityRuleNacosProvider")
public class AuthorityRuleNacosProvider implements DynamicRuleProvider<List<AuthorityRuleEntity>> {

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private SentinelNacosProperties nacosProperties;

	@Autowired
	private Converter<String, List<AuthorityRuleEntity>> converter;

	@Override
	public List<AuthorityRuleEntity> getRules(String appName) throws Exception {
		return SentinelNacosConfigUtil.getRuleEntities4Nacos(
				this.configService,
				this.nacosProperties,
				appName,
				SentinelNacosConfigUtil.AUTHORITY_DATA_ID_POSTFIX,
				AuthorityRuleEntity.class,
				converter
		);
	}
}
