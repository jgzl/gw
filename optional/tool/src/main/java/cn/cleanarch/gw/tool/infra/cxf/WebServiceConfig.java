/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.cleanarch.gw.tool.infra.cxf;

import cn.cleanarch.gw.tool.infra.cxf.service.HelloPortImpl;
import cn.cleanarch.gw.tool.infra.cxf.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import java.util.Collections;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private MetricsProvider metricsProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public Server jaxRsServer() {
        JAXRSServerFactoryBean rsEndpoint = new JAXRSServerFactoryBean();
        rsEndpoint.setBus(bus);
        rsEndpoint.setAddress("/rest/");
        rsEndpoint.setProviders(Collections.singletonList(jacksonJaxbJsonProvider()));
        rsEndpoint.setFeatures(Collections.singletonList(new LoggingFeature()));
        rsEndpoint.setServiceBeans(Collections.singletonList(new UserServiceImpl()));
        return rsEndpoint.create();
    }

    /**
     * 配置一个对象与json转换的工具
     */

    @Bean
    public JacksonJaxbJsonProvider jacksonJaxbJsonProvider() {
        return new JacksonJaxbJsonProvider(objectMapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS);
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new HelloPortImpl(), null, null, new MetricsFeature[]{
                new MetricsFeature(metricsProvider)
        });
        endpoint.publish("/Hello");
        return endpoint;
    }
}
