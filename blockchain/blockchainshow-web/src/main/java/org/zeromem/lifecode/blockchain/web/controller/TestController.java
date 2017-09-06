package org.zeromem.lifecode.blockchain.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zeromem on 2017/6/20.
 */
// @RestController // @Controller + @ResponseBody
@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping(value = "/v1/json", method = RequestMethod.GET)
	String testJson() {
		JSONObject jo = new JSONObject();
		jo.put("message", "success");
		return jo.toJSONString();
	}

	@RequestMapping(value = "/rest/json/{object}/{attribute}",
			method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map testRestJson(
			@PathVariable String object,
			@PathVariable String attribute
	) {
		Map<String, String> map = new HashMap<>();
		map.put("object", object);
		map.put("attribute", attribute);
		return map;
	}

	@RequestMapping(value = "/rest/json/list/{l}",
			method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map testRestList(
			@PathVariable List<BigInteger> l
	) {
		Map<String, Object> map = new HashMap<>();
		List<BigInteger> list = new LinkedList<>();

		map.put("list", l);
		return map;
	}


	@RequestMapping(value = "/rest/json/listAddress/{l}",
			method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map testListAddress(
			@PathVariable List<BigInteger> l
	) {
		Map<String, Object> map = new HashMap<>();
		List<Address> list = new LinkedList<>();
		List<String> res = new LinkedList<>();
		for (BigInteger integer : l) {
			list.add(new Address(integer));
		}

		for (Address address : list) {
			res.add(address.toString());
		}
		map.put("list", res);
		return map;
	}

	@RequestMapping(value = "/rest/json/getAddress/{a}",
			method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map testGetAddress(
			@PathVariable Address a
	) {
		Map<String, Address> map = new HashMap<>();
		map.put("address", new Address("0xa"));

		return map;
	}

	@RequestMapping(value = "/rest/json/getUint/{u}",
			method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	// ZEROMEM Uint256没有接收String的构造器，会抛异常
	public Map testGetUint(
			@PathVariable Uint256 u
	) {
		Map<String, Object> map = new HashMap<>();
		map.put("res", u);
		return map;
	}

	@RequestMapping(value = "/rest/xml/{name}/{value}",
			method = RequestMethod.GET,
			produces = "application/xml")
	public XMLObject testRestXml(
			@PathVariable String name,
			@PathVariable String value
	) {
		return new XMLObject(name, value);
	}


	@RequestMapping(value = "/v1/page", method = RequestMethod.GET)
	String testPage() {
		return "index";
	}
}

@XmlRootElement
class XMLObject {
	String name;
	String value;
	// for serialize and auto construct.
	public XMLObject() {

	}

	public XMLObject(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "XMLObject{" +
				"name='" + name + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}