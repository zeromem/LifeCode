package org.zeromem.lifecode.algorithmaction.dynamicprogramming;

import java.util.*;

/**
 * @author zeromem
 * @date 2017/11/6
 * 最长递增子序列
 * 给定一个序列 An = a1 ,a2 ,  ... , an ,找出最长的子序列使得对所有 i < j ,ai < aj
 */
public class LongestIncSubsequence {
    public static void main(String[] args) {
        LongestIncSubsequence test = new LongestIncSubsequence();
        int[] nums = new int[]{5, 6, 7, 1, 2, 10, 3, 6, 7};
        System.out.println(test.lisLength(nums));
    }

    /**
     *
     * @param nums
     * @return
     */
    public List<Integer> lisLength(int[] nums) {
        int n = nums.length;
        // dp[i] 以nums[i]为子序列末尾元素的最长递增子序列的长度
        int[] dp = new int[n];
        HashMap<Integer, Integer> parent = new HashMap<>(n * 4 / 3 + 1);

        dp[0] = 1;
        int max = 0;
        int posLast = 0;
        for (int i = 1; i < n; i++) {
            // 至少会包含自身
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent.put(i, j);
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                posLast = i;
            }
        }

        LinkedList<Integer> deque = new LinkedList<>();
        deque.addFirst(posLast);

        deque.add(nums[posLast]);
        int pos = posLast;
        Integer posPre;
        while ((posPre = parent.get(pos)) != null) {
            deque.addFirst(nums[posPre]);
            pos = posPre;
        }
        return deque;
    }
}
