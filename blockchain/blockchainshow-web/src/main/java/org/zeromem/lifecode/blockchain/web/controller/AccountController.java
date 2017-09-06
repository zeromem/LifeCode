package org.zeromem.lifecode.blockchain.web.controller;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.zeromem.lifecode.blockchain.web.util.Constants.mongoCollection;
import static org.zeromem.lifecode.blockchain.web.util.Web3jConstant.publicParity;

/**
 * Created by zeromem on 2017/6/20.
 * 账户相关操作
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	@RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map createNewAccount(
			@RequestParam(value = "nickname") String nickname,
			@RequestParam(value = "password") String password
	) throws IOException {
		// TODO: 2017/9/5 多线程不安全
		Map<String, Object> map = new HashMap<>();

		Document filter = new Document();
		filter.put("nickname", nickname);
		Document first = mongoCollection.find(filter).first();
		if (first != null) {
			map.put("error", "account exist!");
			return map;
		}

		String account = publicParity.personalNewAccount(password).send().getAccountId();
		map.put("account", account);
		map.put("nickname", nickname);
		map.put("password", password);

		// save (nickname, password, account) to mongodb
		mongoCollection.insertOne(new Document(map));
		return map;
	}


	@RequestMapping(value = "/{account}/unlock", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map getAccount(
			@PathVariable String account,
			@RequestParam(value = "password") String password
	) throws IOException {
		Map<String, Object> map = new HashMap<>();

		PersonalUnlockAccount response = publicParity.personalUnlockAccount(account, password).send();
		if (response.getError() != null) {
			map.put("error", response.getError().getMessage());
			return map;
		}
		map.put("unlocked", response.getResult());
		return map;
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map unlockAccount(
			@RequestParam(value = "nickname") String nickname,
			@RequestParam(value = "password") String password
	) throws IOException {
		Map<String, Object> map = new HashMap<>();

		Document filter = new Document();
		filter.put("nickname", nickname);
		filter.put("password", password);

		Document first = mongoCollection.find(filter).first();
		if (first == null) {
			map.put("error", "no this account or password error.");
			return map;
		}

		map.put("account", first.getString("account"));

		return map;
	}

}
