package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.*;

/**
 * @author zeromem
 * @date 2018/3/1
 * A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
 * <p>
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.
 * <p>
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 * <p>
 * Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.
 * <p>
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 * <p>
 * Note:
 * <p>
 * Starting point is assumed to be valid, so it might not be included in the bank.
 * If multiple mutations are needed, all mutations during in the sequence must be valid.
 * You may assume start and end string is not the same.
 * Example 1:
 * <p>
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 * <p>
 * return: 1
 * Example 2:
 * <p>
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * <p>
 * return: 2
 * Example 3:
 * <p>
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * <p>
 * return: 3
 */
public class _433MinimumGeneticMutation {
    private static final char[] GENE_BASE = new char[]{'A', 'C', 'G', 'T'};

    public static void main(String[] args) {
        _433MinimumGeneticMutation test = new _433MinimumGeneticMutation();
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = new String[]{
                "AACCGGTA", "AACCGCTA", "AAACGGTA"
        };
        System.out.println(test.minMutation(start, end, bank));
    }

    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(end)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String gene = queue.poll();
                if (end.equals(gene)) {
                    return step;
                }
                for (String nei : neighbors(gene, set)) {
                    queue.add(nei);
                    set.remove(nei);;
                }
            }
            step++;
        }
        return -1;
    }

    public static List<String> neighbors(String word, Set<String> bank) {
        List<String> result = new LinkedList<>();
        char[] cs = word.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char old = cs[i];
            for (char c : GENE_BASE) {
                cs[i] = c;
                String newGene = new String(cs);
                if (bank.contains(newGene)) {
                    result.add(newGene);
                }
            }
            cs[i] = old;
        }
        return result;
    }


}
