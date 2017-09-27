package com.ddb.xaplan.cadre.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Demo")
public class DemoView {
	@ApiModelProperty("demo属性")
	private String demo;

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

}
