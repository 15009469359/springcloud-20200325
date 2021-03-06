package com.atguigu.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

import feign.Feign;

@Configuration
public class ConfigBean {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	// 在配置类中切换Ribbon的轮询算法
	@Bean
	public IRule myRule() {
		return new RandomRule(); // 使用随机算法
	}

	@Bean
	@Scope("prototype")
	public Feign.Builder feignHystrixBuilder() {
		return Feign.builder();
	}
}
