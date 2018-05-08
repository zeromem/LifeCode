package cn.com.ctbri.ctbigdata.smarteyes.controller;

import cn.com.ctbri.ctbigdata.smarteyes.dao.OrderDao;
import cn.com.ctbri.ctbigdata.smarteyes.model.Order;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by luyi on 2017/6/22.
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private OrderDao orderDaoImpl;

	@RequestMapping(value = "/getJson", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getTest(){
		Order order = new Order();
		order.setId("1");
		order.setOrderNo("89757");
		order.setCreateDate(new Date());
		order.setPrice(100);
		JSONObject jsonObject = new JSONObject();
		orderDaoImpl.save(order);
		Order order2 = orderDaoImpl.read("1");
		return JSON.toJSONString(order2);
	}

}
