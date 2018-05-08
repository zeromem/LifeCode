package org.zeromem.spring.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zeromem.spring.mongo.documents.User;
import org.zeromem.spring.mongo.repositories.UserRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeromem on 2017/6/21.
 */
@Controller
@RequestMapping("/mongo")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;

//	@Autowired
//	public UserController(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}


	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	String mongoHello() {
		log.debug("ZEROMEM: start mongo service..");
		return JSON.toJSONString(userRepository.customFind("zero"));
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	@ResponseBody
	Map mongoInsert() {
		User user = new User("hello", "world");
		userRepository.insert(user);
		return new HashMap();
	}
}
