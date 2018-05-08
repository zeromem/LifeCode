package org.zeromem.spring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zeromem.spring.mongo.documents.User;

import static org.junit.Assert.assertEquals;

/**
 * Created by zeromem on 2017/6/21.
 */
public class UserControllerTest {
	UserController controller;

	@Before
	public void setUp() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:mvc-dispatcher-servlet.xml", "classpath:spring-mongo.xml");
		controller = applicationContext.getBean(UserController.class);
	}


	@Test
	public void testMongoHello() throws Exception {
		String res = controller.mongoHello();
		System.out.println(res);
		JSONArray ja = JSON.parseArray(res);
		String lastName = ja.getJSONObject(0).getString("lastName");
		assertEquals(lastName, "mem");
	}

	@Test
	public void testInsert() throws Exception {
		User user = new User("hello", "world");

	}

}