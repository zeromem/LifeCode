package org.zeromem.lifecode.algorithmaction;

import java.util.Arrays;

/**
 * Created by zeromem on 2017/10/16.
 * 魔法王国一共有n个城市,编号为0~n-1号,n个城市之间的道路连接起来恰好构成一棵树。
 小易现在在0号城市,每次行动小易会从当前所在的城市走到与其相邻的一个城市,小易最多能行动L次。
 如果小易到达过某个城市就视为小易游历过这个城市了,小易现在要制定好的旅游计划使他能游历最多的城市,请你帮他计算一下他最多能游历过多少个城市(注意0号城市已经游历了,游历过的城市不重复计算)。

 Input
 输入包括两行,第一行包括两个正整数n(2 ≤ n ≤ 50)和L(1 ≤ L ≤ 100),表示城市个数和小易能行动的次数。
 第二行包括n-1个整数parenti, 对于每个合法的i(0 ≤ i ≤ n - 2),在(i+1)号城市和parent[i]间有一条道路连接。

 Output
 输出一个整数,表示小易最多能游历的城市数量。

 Sample Input
 5 2
 0 1 2 3

 Sample Output
 3

 思路：
 从0出发，找到最大深度的路径，为主线。不在主线上的节点，若要访问x个点，则一定需要2x步。
 主线上的节点，顺序访问时，每个节点只需1步。
 */
public class NeteasyTravelTree {
	public static void main(String[] args) {
		NeteasyTravelTree algo = new NeteasyTravelTree();
		/*int cities = 5;
		int steps = 2;
		int[] parents = {0, 1, 2, 3, 4, 5};
		System.out.println(algo.maxTravelNum(cities, steps, parents));*/

		int c = 10;
		int s = 7;
		int[] p = {0, 1, 2, 2, 0, 5, 3, 7, 5};
		System.out.println(algo.maxTravelNum(c, s, p));
	}


	/**
	 * 树的最大深度。
	 * @param parents
	 * @return
	 */
	public static int maxDeep(int[] parents) {
		int result = 0;
		int[] deep = new int[parents.length + 1];
		deep[0] = 0;
		for (int i = 0; i < parents.length; i++) {
			// 题目输入保证节点x的父节点编号一定小于x，所以可以按数组顺序遍历
			deep[i + 1] = deep[parents[i]] + 1;
			if (deep[i + 1] > result) {
				result = deep[i + 1];
			}
		}
		return result;
	}

	public int maxTravelNum(int numCities, int numMaxSteps, int[] parents) {
		int maxDeep = maxDeep(parents);
		if (numMaxSteps <= maxDeep) {
			return numMaxSteps + 1;
		}

		int restSteps = numMaxSteps - maxDeep;
		int restCities = numCities - (maxDeep + 1);

		if (restSteps / 2 >= restCities) {
			return numCities;
		}

		return maxDeep + 1 + restSteps / 2;
	}
}
