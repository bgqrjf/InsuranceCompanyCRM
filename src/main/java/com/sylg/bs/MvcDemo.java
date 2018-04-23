package com.sylg.bs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sylg.bs.common.config.kafka.Producer;
import com.sylg.bs.user.AESUtil;

@Controller
@RequestMapping("/xxx")
@Api("SwaggerDemoController相关api")//访问 http://localhost:8000/swagger-ui.html

public class MvcDemo {
	@Autowired
	private Producer producer;

	/**
	 * 
	 * @Description: ajax访问的方法,使用实体类接收表单序列化的参数
	 * @author QSNP242
	 * @param user
	 * @return String
	 * @throws
	 * @date 2018年3月29日
	 */
	@RequestMapping("/m1")
	@ResponseBody
	public UserBean test(UserBean list) {
		System.out.println("ok");
		System.out.println(list.toString());
		return null;
	}

	/**
	 * 
	 * @Description: REST测试
	 * @author QSNP242
	 * @param userCode
	 * @param orderUrl
	 * @return String
	 * @throws
	 * @date 2018年4月4日
	 */
	@RequestMapping("/{userCode}")
	public String test2(@PathVariable("userCode") String userCode,
			String orderUrl) {
		System.out.println(userCode);
		System.out.println(orderUrl);
		StringBuilder sb = new StringBuilder("https://www.baidu.com");
		sb.append("?userCode=" + userCode);
		return "redirect:" + sb.toString();
	}

	@RequestMapping("/jsp")
	@ResponseBody
	public String test3() {
        System.out.println(".........");
		return "bgqrj";
	}

	@RequestMapping(value = "/urlRedrict")
	public String redirectPage(HttpServletRequest request,
			HttpServletResponse response) {
		// 拼接Url
		String orderUrl = "www.baidu.com";
		String agentCode = "8030088";
		request.setAttribute("agentCode", agentCode);
		request.setAttribute("orderUrl", orderUrl);
		return "index";
	}

	@ApiOperation(value = "获取用户列表", notes = "")
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没填好"),
			@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对") })
	@RequestMapping(value = "/urlRedrict2")
	public String redirectPage1(HttpServletRequest request,
			HttpServletResponse response) {
		String code = request.getParameter("userCode");
		System.out.println(code);
		String code1 = AESUtil.decrypt(code, "WhbnSDg4PRl5OQAd");
		System.out.println(code1);
		return "index";
	}
}
