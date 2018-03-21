package org.zeromem.lifecode.algorithmaction.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zeromem
 * @date 2018/3/20
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class _150EvaluateReversePolishNotation {
    public static void main(String[] args) {

    }

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.addLast(stack.removeLast() + stack.removeLast());
                    break;
                case "-":
                    stack.addLast(-(stack.removeLast() - stack.removeLast()));
                    break;
                case "*":
                    stack.addLast(stack.removeLast() * stack.removeLast());
                    break;
                case "/":
                    Integer b = stack.removeLast();
                    Integer a = stack.removeLast();
                    stack.addLast(a / b);
                    break;
                default:
                    stack.addLast(Integer.valueOf(token));
            }
        }
        return stack.removeLast();
    }
}
