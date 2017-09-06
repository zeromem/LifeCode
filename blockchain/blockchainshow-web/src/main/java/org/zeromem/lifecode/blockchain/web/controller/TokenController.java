package org.zeromem.lifecode.blockchain.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.zeromem.lifecode.blockchain.web.util.Web3jConstant.token;

/**
 * Created by zeromem on 2017/9/4.
 * 代币相关操作
 */
@Controller
@RequestMapping("/token")
public class TokenController {
	@RequestMapping(value = "/{userid}/balance", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map balanceOf(
			@PathVariable Address userid
	) throws IOException, ExecutionException, InterruptedException {
		Map<String, Object> map = new HashMap<>();
		try {
			BigInteger balance = token.balancesOf(userid).get().getValue();
			map.put("balance", balance);
		} catch (NumberFormatException e) {
			map.put("error", "Wrong user address format");
		}

		return map;
	}

	@RequestMapping(value = "/{userid}/transformation", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map transfer(
			@PathVariable(value = "userid") Address from,
			@RequestParam Address to,
			@RequestParam Integer amount
	) throws IOException, ExecutionException, InterruptedException {
		Map<String, Object> map = new HashMap<>();
		try {
			token.transfer(from, to, new Uint256(amount)).get();
			BigInteger tokenOfFrom = token.balancesOf(from).get().getValue();
			BigInteger tokenOfTo = token.balancesOf(to).get().getValue();
			map.put("tokenOfFrom", tokenOfFrom);
			map.put("tokenOfTo", tokenOfTo);
		} catch (NumberFormatException e) {
			map.put("error", "Wrong user address format");
		}
		return map;
	}

	@RequestMapping(value = "/{userid}/issue", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map issue(
			@PathVariable Address userid,
			@RequestParam Integer amount
	) throws IOException, ExecutionException, InterruptedException {
		Map<String, Object> map = new HashMap<>();
		try {
			Address owner = token.owner().get();
			if (!userid.equals(owner)) {
				map.put("error", "only contract owner can issue token.");
			} else {
				token.mint(new Uint256(amount)).get();

				// 获取owner当前最新的token量
				BigInteger currentAmount = token.balancesOf(owner).get().getValue();
				map.put("ownerTokenAmount", currentAmount);
			}
		} catch (NumberFormatException e) {
			map.put("error", "Wrong user address format");
		}
		return map;
	}
}
