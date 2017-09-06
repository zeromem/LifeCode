package org;

import com.alibaba.fastjson.JSONObject;
import com.sun.jndi.cosnaming.IiopUrl;
import org.omg.CORBA.OBJ_ADAPTER;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.core.methods.response.AbiDefinition;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zeromem on 2017/9/4.
 */
public class NewAddressTest {
	public static void main(String[] args) {
		Address a1 = new Address("0xa");
		System.out.println(a1);
		System.out.println(a1.toString());
		System.out.println(a1.getValue());
		Map<String, Object> map = new HashMap<>();
		map.put("address", a1.toString());


		// controller会将map结果自动转换成json格式
		System.out.println(new JSONObject(map));

	}
}
