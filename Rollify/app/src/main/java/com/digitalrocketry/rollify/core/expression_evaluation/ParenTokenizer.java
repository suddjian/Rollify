package com.digitalrocketry.rollify.core.expression_evaluation;

import java.util.List;

/**
 * Created by David Aaron Suddjian on 9/8/2015.
 */
public class ParenTokenizer implements Tokenizer {
    @Override
    public boolean tryTokenize(TokenizationContext context, StringScanner sc) {
        if (sc.next() == '(') {
            List<Token> contents = new TokenizationContext(context, true).tokenize();
            Token multiplier;
            if (context.lastTokenWasnumber()) {
                multiplier = context.getLastToken();
                List<Token> outputTokens = context.getOutputTokens();
                outputTokens.remove(outputTokens.size() - 1);
            } else {
                multiplier = new NumberToken(1);
            }
            context.commitToken(new TokenGroup(multiplier, contents));
            return true;
        } else if (context.isInParentheses() && sc.next() == ')') {
            context.finish();
            return true;
        } else {
            return false;
        }
    }
}
