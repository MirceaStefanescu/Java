package org.behavioral.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

//class that will allow what kind of pattern you want to convert
public class InfixToPostfixPattern implements Pattern {

    // A utility function to return
    // precedence of a given operator
    // Higher returned value means
    // higher precedence
    static int prec(char ch) {
        return switch (ch) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    @Override
    public String conversion(String exp) {

        // Initializing empty String for result
        String result = "";

        // Initializing empty stack
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);

            // If the scanned character is an
            // operand, add it to output.
            if (Character.isLetterOrDigit(c))
                result += c;

                // If the scanned character is an '(',
                // push it to the stack.
            else if (c == '(')
                stack.push(c);

                // If the scanned character is an ')',
                // pop and output from the stack
                // until an '(' is encountered.
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.peek();
                    stack.pop();
                }
                stack.pop();
            }

            // An operator is encountered
            else {
                while (!stack.isEmpty() && prec(c) <= prec(stack.peek())) {
                    result += stack.peek();
                    stack.pop();
                }
                stack.push(c);
            }
        }

        // Pop all the operators from the stack
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            result += stack.peek();
            stack.pop();
        }

        return result;
    }
}

