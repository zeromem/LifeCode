package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zeromem
 * @date 2018/3/1
 */
public class _637AverageofLevelsinBinaryTree {
    public static void main(String[] args) {

    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(sum / size);
        }
        return result;
    }
}
