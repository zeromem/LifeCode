package org.zeromem.lifecode.algorithmaction.dynamicprogramming;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONCreator;

import java.util.HashMap;

/**
 * @author zeromem
 * @date 2017/11/13
 * <p>
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.
 * <p>
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 * <p>
 * Return the maximum profit you can make.
 * <p>
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * <p>
 * Define dp array:
 * hold[i] : The maximum profit of holding stock until day i;
 * notHold[i] : The maximum profit of not hold stock until day i;
 * <p>
 * dp transition function:
 * For day i, we have two situations:
 * <p>
 * Hold stock:
 * (1) We do nothing on day i: hold[i - 1];
 * (2) We buy stock on day i: notHold[i - 1] - prices[i - 1] - fee;
 * <p>
 * Not hold stock:
 * (1) We do nothing on day i: notHold[i - 1];
 * (2) We sell stock on day i: hold[i - 1] + prices[i - 1];
 */
public class _714BestTimeToBuyAndSellStockWithFee {
    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        _714BestTimeToBuyAndSellStockWithFee test = new _714BestTimeToBuyAndSellStockWithFee();
        System.out.println(test.maxProfit(prices, fee));
    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] hold = new int[n + 1];
        int[] notHold = new int[n + 1];
        hold[0] = Integer.MIN_VALUE;
        for (int i = 1; i < n + 1; i++) {
            hold[i] = Math.max(hold[i - 1], notHold[i - 1] - prices[i - 1] - fee);
            notHold[i] = Math.max(notHold[i - 1], hold[i - 1] + prices[i - 1]);
        }

        return notHold[n];
    }
}
