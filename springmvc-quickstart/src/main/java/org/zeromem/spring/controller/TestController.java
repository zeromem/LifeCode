package org.zeromem.spring.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zeromem on 2017/6/20.
 */
@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping(value = "/v1/json", method = RequestMethod.GET)
	@ResponseBody
	String testJson() {
		JSONObject jo = new JSONObject();
		jo.put("message", "success");
		return jo.toJSONString();
	}

	@RequestMapping(value = "/v1/page", method = RequestMethod.GET)
	String testPage() {
		return "index";
	}
}
