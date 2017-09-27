package com.ddb.xaplan.cadre.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.view.DemoView;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class DemoController {

	@ApiOperation(value = "demo controller")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "demo", value = "demo", required = true, paramType = "query", dataType = "String") })
	@GetMapping("/demo/getDemo")
	// @PreAuthorize("hasAuthority('ROLE_USER')")//添加方法权限
	public DataInfo<DemoView> postGuanzhu(HttpServletRequest request, @RequestParam(name = "demo") String demo) {
		DataInfo<DemoView> dataInfo = new DataInfo<>();
		// 获取用户数据
		// String userid = UserInfoTool.getUserid(request,redisTemplate);
		DemoView demoView = new DemoView();
		demoView.setDemo(demo);
		dataInfo.setData(demoView);
		return dataInfo;
	}

}
