package org.zeromem.lifecode.algorithmaction.geeksforgeeks;

/**
 * @author zeromem
 * @date 2018/3/6
 * Write a function to detect if two trees are isomorphic.
 * Two trees are called isomorphic if one of them can be obtained from other by a series of flips,
 * i.e. by swapping left and right children of a number of nodes.
 * Any number of nodes at any level can have their children swapped.
 * Two empty trees are isomorphic.
 */
public class TreeIsomorphism {
    public static void main(String[] args) {

    }

    public boolean isIsomorphic(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.data != root2.data) {
            return false;
        }
        return (isIsomorphic(root1.left, root2.left) && isIsomorphic(root1.right, root2.right)) ||
                (isIsomorphic(root1.left, root2.right) && isIsomorphic(root1.right, root2.left));
    }
}

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right;
    }
}
