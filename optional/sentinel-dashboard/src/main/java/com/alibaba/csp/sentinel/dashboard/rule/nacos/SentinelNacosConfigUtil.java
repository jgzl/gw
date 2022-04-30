/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
public final class SentinelNacosConfigUtil {

	public static final String DEGRADE_DATA_ID_POSTFIX = "-degrade-rules";

	public static final String FLOW_DATA_ID_POSTFIX = "-flow-rules";

	public static final String PARAM_FLOW_DATA_ID_POSTFIX = "-param-flow-rules";

	public static final String SYSTEM_DATA_ID_POSTFIX = "-system-rules";

	public static final String AUTHORITY_DATA_ID_POSTFIX = "-authority-rules";

	public static final String GATEWAY_FLOW_RULE_ID_POSTFIX = "-gw-flow-rules";

	public static final String GATEWAY_API_ID_POSTFIX = "-gw-api-group-rules";

	public static final String CLUSTER_MAP_DATA_ID_POSTFIX = "-cluster-map";

	/**
	 * cc for `cluster-client`
	 */
	public static final String CLIENT_CONFIG_DATA_ID_POSTFIX = "-cc-config";

	/**
	 * cs for `cluster-server`
	 */
	public static final String SERVER_TRANSPORT_CONFIG_DATA_ID_POSTFIX = "-cs-transport-config";

	public static final String SERVER_FLOW_CONFIG_DATA_ID_POSTFIX = "-cs-flow-config";

	public static final String SERVER_NAMESPACE_SET_DATA_ID_POSTFIX = "-cs-namespace-set";

	private SentinelNacosConfigUtil() {
	}


	/**
	 * 方法实现说明:从nacos服务上获取配置
	 * @author:vlfac
	 * @param configService:nacos配置服务
	 * @param appName 应用微服务名称
	 * @param postfix  SentinelNacosConfigUtil.FLOW_DATA_ID_POSTFIX
	 * @param clazz:反序列化class类型
	 * @return:
	 * @exception:
	 * @date:2019/12/2 14:38
	 */
	public static <T> List<T> getRuleEntities4Nacos(ConfigService configService, SentinelNacosProperties nacosProperties, String appName, String postfix,
			Class<T> clazz,
			Converter<String, List<T>> converter) throws NacosException {
		//去nacos注册中心获取配置
		String rules = configService.getConfig(genDataId(appName, postfix),
				Objects.toString(nacosProperties.getGroup(),Constants.DEFAULT_GROUP),
				3000
		);

		if (StringUtil.isEmpty(rules)) {
			return new ArrayList<>();
		}
		return converter.convert(rules);
	}

	/**
	 * 方法实现说明:存在规则到nacos server上
	 * @author:vlfac
	 * @param configService nacos配置服务
	 * @param app:微服务名称
	 * @param postfix: SentinelNacosConfigUtil.FLOW_DATA_ID_POSTFIX
	 * @param rules:规则列表
	 * @return:
	 * @exception:
	 * @date:2019/12/2 14:49
	 */
	public static <T> void setRuleString2Nacos(ConfigService configService, SentinelNacosProperties nacosProperties, String app, String postfix, List<T> rules,
			Converter<List<T>, String> converter) throws NacosException {
		AssertUtil.assertNotBlank(app, "app name not be empty");

		//生成数据ID
		String dataId = genDataId(app, postfix);

		//发布配置
		configService.publishConfig(dataId,
				Objects.toString(nacosProperties.getGroup(), Constants.DEFAULT_GROUP),
				converter.convert(rules)
		);
	}

	/**
	 * 方法实现说明:生成数据ID
	 * @author:vlfac
	 * @param appName 微服务名称
	 * @param postfix 规则后缀 SentinelNacosConfigUtil.FLOW_DATA_ID_POSTFIX
	 * @return:
	 * @exception:
	 * @date:2019/12/2 14:34
	 */
	private static String genDataId(String appName, String postfix) {
		return appName + postfix;
	}
}
