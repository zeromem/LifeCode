package org.zeromem.lifecode.algorithmaction.dynamicprogramming;

import java.util.Arrays;

/**
 * @author zeromem
 * @date 2017/11/29
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 * <p>
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 * <p>
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */
public class _322CoinChange {
    public static void main(String[] args) {
        _322CoinChange test = new _322CoinChange();
        int[] coins = new int[]{1};
        int amount = 0;
        System.out.println(test.coinChange(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.sort(coins);
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int coin : coins) {
            if (coin > amount) {
                break;
            }
            dp[coin] = 1;
        }

        for (int i = coins[0]; i <= amount; i++) {
            for (int coin : coins) {
                if (i + coin <= amount && i + coin >= 0 && dp[i] != Integer.MAX_VALUE) {
                    dp[i + coin] = Math.min(dp[i] + 1, dp[i + coin]);
                }
            }
        }

        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }

}
