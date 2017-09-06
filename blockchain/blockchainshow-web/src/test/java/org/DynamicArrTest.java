package org;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zeromem on 2017/9/5.
 */
public class DynamicArrTest {
	public static void main(String[] args) {
		List<Address> list = new LinkedList<>();
		list.add(new Address("0x1"));
		list.add(new Address("0x2"));
		DynamicArray<Address> arr = new DynamicArray<>(list);
		System.out.println(arr.getValue());

	}
}
