package com.digitalrocketry.rollify.core.expression_evaluation;

import java.util.Stack;

/**
 * Created by David Aaron Suddjian on 9/1/2015.
 */
public class NumberToken implements Token {
    private long num;

    public NumberToken(long num) {
        this.num = num;
    }

    @Override
    public void Operate(Stack<Long> stack) {
        stack.push(num);
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}