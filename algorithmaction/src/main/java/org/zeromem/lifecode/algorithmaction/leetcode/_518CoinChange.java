package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * Created by zeromem on 2017/3/7.
 * You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.
 * <p>
 * Note: You can assume that
 * <p>
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 * Example 1:
 * <p>
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 * <p>
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 * <p>
 * Input: amount = 10, coins = [10]
 * Output: 1
 */
public class _518CoinChange {
	public static void main(String[] args) {
		_518CoinChange test = new _518CoinChange();
		int[] coins = {1, 2, 5};
		System.out.println(test.change(5, coins));
	}


	/**
	 * f(i) = sigma_j[f(i-coins[j])]
	 *
	 * @param amount
	 * @param coins
	 * @return
	 */
	public int change(int amount, int[] coins) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = 0;
		}

		// coin:coins一定要写在外层循环，防止重复
		for (int coin : coins) {
			for (int i = 0; i < amount; i++) {
				if (i + coin <= amount) {
					dp[i + coin] += dp[i];
				}
			}
		}
		return dp[amount];
	}
}
