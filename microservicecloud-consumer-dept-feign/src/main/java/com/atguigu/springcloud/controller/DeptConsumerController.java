package com.atguigu.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptClientService;

/**
 * 注掉部分为使用Ribbon编程，现在改为用Feign，同样有负载均衡效果
 * @author 27287
 *
 */
@RestController
public class DeptConsumerController {

//	private static final String REST_URL_PREFIX = "http://localhost:8001";

//	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";

	@Autowired
	private DeptClientService service;
	
//	@Autowired
//	private RestTemplate restTemplate;

	@RequestMapping(value = "/consumer/dept/add")
	public boolean add(Dept dept) {
//		return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
		return this.service.add(dept);
	}

	@RequestMapping(value = "/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
//		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
		return this.service.get(id);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/consumer/dept/list")
	public List<Dept> list() {
//		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);

		return this.service.list();
	}

//	@RequestMapping(value = "/consumer/dept/discovery")
//	public Object discovery() {
//		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
//	}
}
