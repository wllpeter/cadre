package com.ddb.xaplan.cadre;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class XaPlanCadreApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(XaPlanCadreApplication.class).web(true).run(args);
	}

}
