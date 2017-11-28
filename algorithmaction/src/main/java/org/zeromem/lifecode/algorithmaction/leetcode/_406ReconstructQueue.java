package org.zeromem.lifecode.algorithmaction.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zeromem on 2017/5/31.
 */
public class _406ReconstructQueue {
	public static void main(String[] args) {
		_406ReconstructQueue test = new _406ReconstructQueue();
		int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
		System.out.println(JSON.toJSONString(test.reconstructQueue(people)));

		LinkedList<Integer> list = new LinkedList<>();
		list.add(10, 1);
	}

	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, (p1, p2) -> (p1[0] != p2[0]) ? (p2[0] - p1[0]) : (p1[1] - p2[1]));
		System.out.println(JSON.toJSONString(people));
		List<int[]> res = new LinkedList<>();
		for (int[] person : people) {
			res.add(person[1], person);
		}
		return res.toArray(new int[people.length][]);
	}
}
