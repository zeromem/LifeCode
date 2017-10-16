package org.zeromem.lifecode.algorithmaction;

/**
 * Created by zeromem on 2017/10/16.
 小易有一个长度为N的正整数数列A = {A[1], A[2], A[3]..., A[N]}。
 牛博士给小易出了一个难题:
 对数列A进行重新排列,使数列A满足所有的A[i] * A[i + 1] (1 ≤ i ≤ N - 1)都是4的倍数。
 小易现在需要判断一个数列是否可以重排之后满足牛博士的要求。

 Input
 输入的第一行为数列的个数t(1 ≤ t ≤ 10),
 接下来每两行描述一个数列A,第一行为数列长度n(1 ≤ n ≤ 10^5)
 第二行为n个正整数Ai

 Output
 对于每个数列输出一行表示是否可以满足牛博士要求,如果可以输出Yes,否则输出No。

 Sample Input
 2
 3
 1 10 100
 4
 1 2 3 4

 Sample Output
 Yes
 No

 思路：
 存在2的倍数，所有2的倍数在队列前面相邻排列，需要一个4的倍数连接剩下的数，奇数最多和4的倍数数量相等，要求 countMod4 >= countOdd
 没有2的倍数，队列前面连续的2的倍数可以改放一个奇数，countMod4 >= countOdd - 1
 */
public class NeteasyMultipleOfFourArray {
	public static void main(String[] args) {
		NeteasyMultipleOfFourArray algo = new NeteasyMultipleOfFourArray();
		System.out.println(algo.satisfy(new int[]{1, 10, 100}));
		System.out.println(algo.satisfy(new int[]{1, 2, 3, 4}));
	}

	public boolean satisfy(int[] array) {
		int multiOf4Counter = 0; // 4的倍数的数量
		int onlyMultiOf2Counter = 0; // 2的倍数（不为4的倍数）的数量
		int oddCounter = 0; // 奇数数量

		for (int i : array) {
			if ((i & 3) == 0) {
				multiOf4Counter++;
			} else if ((i & 1) == 0) {
				onlyMultiOf2Counter++;
			} else {
				oddCounter++;
			}
		}

		if (onlyMultiOf2Counter != 0) {
			return multiOf4Counter >= oddCounter;
		}

		return multiOf4Counter >= oddCounter - 1;

//		return multiOf4Counter >= oddCounter - (onlyMultiOf2Counter > 0 ? 1 : 0);
	}


}
