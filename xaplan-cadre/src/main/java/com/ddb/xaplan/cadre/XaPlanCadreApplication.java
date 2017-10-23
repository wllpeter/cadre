package com.ddb.xaplan.cadre;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableEurekaClient
@EnableDiscoveryClient
@EnableAsync
@SpringBootApplication
public class XaPlanCadreApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(XaPlanCadreApplication.class).web(true).run(args);
	}

}
