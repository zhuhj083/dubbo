/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.config.spring.context.annotation;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ModuleConfig;
import org.apache.dubbo.config.MonitorConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * As  a convenient and multiple {@link EnableDubboConfigBinding}
 * in default behavior , is equal to single bean bindings with below convention prefixes of properties:
 * <ul>
 * <li>{@link ApplicationConfig} binding to property : "dubbo.application"</li>
 * <li>{@link ModuleConfig} binding to property :  "dubbo.module"</li>
 * <li>{@link RegistryConfig} binding to property :  "dubbo.registry"</li>
 * <li>{@link ProtocolConfig} binding to property :  "dubbo.protocol"</li>
 * <li>{@link MonitorConfig} binding to property :  "dubbo.monitor"</li>
 * <li>{@link ProviderConfig} binding to property :  "dubbo.provider"</li>
 * <li>{@link ConsumerConfig} binding to property :  "dubbo.consumer"</li>
 * </ul>
 * <p>
 * In contrast, on multiple bean bindings that requires to set {@link #multiple()} to be <code>true</code> :
 * <ul>
 * <li>{@link ApplicationConfig} binding to property : "dubbo.applications"</li>
 * <li>{@link ModuleConfig} binding to property :  "dubbo.modules"</li>
 * <li>{@link RegistryConfig} binding to property :  "dubbo.registries"</li>
 * <li>{@link ProtocolConfig} binding to property :  "dubbo.protocols"</li>
 * <li>{@link MonitorConfig} binding to property :  "dubbo.monitors"</li>
 * <li>{@link ProviderConfig} binding to property :  "dubbo.providers"</li>
 * <li>{@link ConsumerConfig} binding to property :  "dubbo.consumers"</li>
 * </ul>
 *
 * @see EnableDubboConfigBinding
 * @see DubboConfigConfiguration
 * @see DubboConfigConfigurationRegistrar
 * @since 2.5.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(DubboConfigConfigurationRegistrar.class)
public @interface EnableDubboConfig {

    /**
     * 说明：
     *
     * 使用Import标签指向了DubboConfigConfigurationRegistrar类,DubboConfigConfigurationRegistrar实现了ImportBeanDefinitionRegistrar
     *
     * 当在@Configuration标注的Class上
     *  使用@Import引入了一个ImportSelector实现类后，会把实现类中返回的Class名称都定义为bean。
     *  使用@Import引入一个ImportBeanDefinitionRegistrar接口的实现类后，会在实现类中直接通过BeanDefinitionRegistry对象注册bean。
     *
     * 1.ImportBeanDefinitionRegistrar的接口方法registerBeanDefinitions，返回类型是void，且多了一个BeanDefinitionRegistry类型的参数，
     * 它允许我们直接通过BeanDefinitionRegistry对象注册bean。
     * 在实现类中可以使用ClassPathBeanDefinitionScanner进行扫描并自动注册，它是ClassPathScanningCandidateComponentProvider的子类，
     * 所以还是可以添加相同的TypeFilter，然后通过scanner.scan(basePackages)扫描指定的basePackage下满足条件的Class并注册它们为bean。
     *
     * 2.另一个实现是ImportSelector接口，其中只有一个方法selectImports作用是指定需要注册为bean的Class名称。
     */

    /**
     * It indicates whether binding to multiple Spring Beans.
     *
     * @return the default value is <code>false</code>
     * @revised 2.5.9
     */
    boolean multiple() default true;

}
