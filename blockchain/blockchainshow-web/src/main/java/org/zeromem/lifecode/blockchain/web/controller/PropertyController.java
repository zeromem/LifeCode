package org.zeromem.lifecode.blockchain.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.zeromem.lifecode.blockchain.web.util.Web3jConstant.property;

/**
 * Created by zeromem on 2017/9/5.
 */
@Controller
@RequestMapping("/property")
public class PropertyController {
	@RequestMapping(value = "/{propId}/currentOwner", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map currentOwner(
			@PathVariable BigInteger propId
	) throws IOException, ExecutionException, InterruptedException {
		Map<String, Object> map = new HashMap<>();
		try {
			Address owner = property.getCurrent(new Uint256(propId)).get();
			// ZEROMEM: 给到前台的结果不能是Address，应该是一个友好的值
			// ZEROMEM: spring将map转成json的时候owner会产生多余信息，应该先调用toString
			map.put("currentOwner", owner == null ? null : owner.toString());
		} catch (NumberFormatException e) {
			map.put("error", "Wrong property id format");
		}
		return map;
	}

	@RequestMapping(value = "/{propId}/listOwners", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map listOwners(
			@PathVariable BigInteger propId
	) throws IOException, ExecutionException, InterruptedException {
		Map<String, Object> map = new HashMap<>();
		try {
			// TODO: 2017/9/5 第二个参数指定获取的list的最大长度，以后可以写在配置文件中
			// spring将map转换成json时，每个address会产生多余信息，需要显式调用toString
			List<String> addresses = property
					.getList(new Uint256(propId), new Uint256(10)).get()
					.getValue()
					.stream().map(Address::toString).collect(Collectors.toList());
			map.put("ownerList", addresses);
		} catch (NumberFormatException e) {
			map.put("error", "Wrong property id format");
		}
		return map;
	}

	@RequestMapping(value = "/{propId}/transformation", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map transfer(
			@PathVariable BigInteger propId,
			@RequestParam Address from,
			@RequestParam Address to
	) throws IOException, ExecutionException, InterruptedException {
		Map<String, Object> map = new HashMap<>();
		try {
			property.transfer(new Uint256(propId), from, to).get();

			// 数据上链后，从链上获取当前最新owner
			String curOwner = property.getCurrent(new Uint256(propId)).get().toString();
			map.put("propertyId", propId);
			map.put("currentOwner", curOwner);
		} catch (NumberFormatException e) {
			map.put("error", "Wrong property id format");
		}
		return map;
	}

	@RequestMapping(value = "/{propId}/firstOwn", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map firstOwn(
			@PathVariable BigInteger propId,
			@RequestParam Address firstOwner
	) throws IOException, ExecutionException, InterruptedException {
		Map<String, Object> map = new HashMap<>();
		try {
			property.firstOwn(new Uint256(propId), firstOwner).get();

			// 上链后，从链上获取最新的owner
			String curOwner = property.getCurrent(new Uint256(propId)).get().toString();
			map.put("propertyId", propId);
			map.put("currentOwner", curOwner);
		} catch (NumberFormatException e) {
			map.put("error", "Wrong property id format");
		}
		return map;
	}
}
