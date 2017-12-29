package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author zeromem
 * @date 2017/12/29
 * <p>
 * We are given an array asteroids of integers representing asteroids in a row.
 * <p>
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * <p>
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * <p>
 * Example 1:
 * Input:
 * asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation:
 * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 * Example 2:
 * Input:
 * asteroids = [8, -8]
 * Output: []
 * Explanation:
 * The 8 and -8 collide exploding each other.
 * Example 3:
 * Input:
 * asteroids = [10, 2, -5]
 * Output: [10]
 * Explanation:
 * The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
 * Example 4:
 * Input:
 * asteroids = [-2, -1, 1, 2]
 * Output: [-2, -1, 1, 2]
 * Explanation:
 * The -2 and -1 are moving left, while the 1 and 2 are moving right.
 * Asteroids moving the same direction never meet, so no asteroids will meet each other.
 * Note:
 * <p>
 * The length of asteroids will be at most 10000.
 * Each asteroid will be a non-zero integer in the range [-1000, 1000]..
 */
public class _735AsteroidCollision {
    public static void main(String[] args) {
        _735AsteroidCollision test = new _735AsteroidCollision();
        int[] asteroids = new int[]{-2, -1, 1, 2};
        System.out.println(Arrays.toString(test.asteroidCollision(asteroids)));
    }

    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids.length <= 1) {
            return asteroids;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int size : asteroids) {
            if (size > 0) {
                deque.addLast(size);
            } else {
                while (true) {
                    Integer top = deque.peekLast();
                    if (top == null) {
                        deque.addLast(size);
                        break;
                    } else if (top < 0) {
                        deque.addLast(size);
                        break;
                    } else if (top + size > 0) {
                        break;
                    } else if (top + size == 0) {
                        deque.removeLast();
                        break;
                    } else {
                        deque.removeLast();
                    }
                }
            }
        }

        return Arrays.stream(deque.toArray(new Integer[]{})).mapToInt(i -> i).toArray();
    }
}
