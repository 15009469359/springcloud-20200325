package com.atguigu.myrule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

public class MyRandomRule extends AbstractLoadBalancerRule {

	private int total = 0;
	private int currentIndex = 0;

	@Override
	public Server choose(Object key) {
		return choose(getLoadBalancer(), key);
	}

	private Server choose(ILoadBalancer lb, Object key) {
		if (null == lb) {
			return null;
		}
		Server server = null;
		while (null == server) {
			if (Thread.interrupted()) {
				return null;
			}
			List<Server> upServerList = lb.getReachableServers();
			List<Server> allServerList = lb.getAllServers();
			int size = allServerList.size();
			if (size == 0) {
				return null;
			}
			if (total < 5) {
				server = upServerList.get(currentIndex);
				total++;
			} else {
				total = 0;
				currentIndex++;
				if (currentIndex < upServerList.size()) {
					currentIndex = 0;
				}
			}

			if (server == null) {
				Thread.yield();
				continue;
			}
			if (server.isAlive()) {
				return (server);
			}
			server = null;
			Thread.yield();
		}
		return server;
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {

	}
}
