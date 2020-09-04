package com.example.swagger;

import org.leibnizcenter.cfg.algebra.semiring.dbl.LogSemiring;
import org.leibnizcenter.cfg.category.Category;
import org.leibnizcenter.cfg.category.nonterminal.NonTerminal;
import org.leibnizcenter.cfg.category.terminal.Terminal;
import org.leibnizcenter.cfg.category.terminal.stringterminal.CaseInsensitiveStringTerminal;
import org.leibnizcenter.cfg.category.terminal.stringterminal.ExactStringTerminal;
import org.leibnizcenter.cfg.earleyparser.Parser;
import org.leibnizcenter.cfg.grammar.Grammar;
import org.leibnizcenter.cfg.token.Tokens;

public class Example {
    /**
     * const variable = (token) => token.match(/[_a-zA-Z]+[_a-zA-Z0-9]*$/);
     * const constv = (token) => token.match(/[0-9]+$/);
     * const and = (token) => token.match(/&&/);
     * const or = (token) => token.match(/(\|\|)/);
     * const not = (token) => token.match(/!/);
     * const leftP = (token) => token.match(/\(/);
     * const rightP = (token) => token.match(/\)/);
     *
     */
    // NonTerminals are just wrappers around a string
    private static final NonTerminal S = Category.nonTerminal("S");
    private static final NonTerminal E = Category.nonTerminal("E");
    private static final NonTerminal E1 = Category.nonTerminal("E1");
    private static final NonTerminal E2 = Category.nonTerminal("E2");
    private static final NonTerminal V = Category.nonTerminal("V");
    private static final NonTerminal V1 = Category.nonTerminal("V1");
    private static final NonTerminal V2 = Category.nonTerminal("V2");
    private static final NonTerminal T = Category.nonTerminal("T");
    private static final NonTerminal P1 = Category.nonTerminal("P1");
    private static final NonTerminal P2 = Category.nonTerminal("P2");
    private static final NonTerminal F = Category.nonTerminal("F");


    // Terminal types are realized by implementing the Terminal interface, specifically the function hasCategory. Terminal is a functional interface.
    // Note that tokens can be of multiple terminal types (homographs: "bank" as a noun or "bank" as a verb), so you can use this method to pool many words to a single terminal
    private static final Terminal<String> variable = token -> token.obj.matches("(A|B)");
    // Some utility terminal types are pre-defined:
    private static final Terminal<String> constv = token -> token.obj.matches("[0-9]+$");
    private static final Terminal<String> and = token -> token.obj.matches("&&");
    private static final Terminal<String> or = token -> token.obj.matches("||");
    private static final Terminal<String> not = token -> token.obj.matches("!");
    private static final Terminal<String> leftP = token -> token.obj.matches("\\(");
    private static final Terminal<String> rightP = token -> token.obj.matches("\\)");

    private static final Grammar grammar = new Grammar.Builder("test")
            .setSemiring(LogSemiring.get()) // If not set, defaults to Log semiring which is probably what you want. The builder takes care of converting probabilties to semiring elements
            .addRule(
                    1.0,   // Probability between 0.0 and 1.0, defaults to 1.0
                    S,     // Left hand side of the rule
                    E // Right hand side of the rule
            )
            .addRule(
                    1.0,   // Probability between 0.0 and 1.0, defaults to 1.0
                    S,     // Left hand side of the rule
                    V1 // Right hand side of the rule
            )
            .addRule(
                    0.8,   // Probability between 0.0 and 1.0, defaults to 1.0
                    S,     // Left hand side of the rule
                    E,T,E // Right hand side of the rule
            )
            .addRule(
                    0.4,
                    E,
                    V2, T, V2 // eg. (A&&B)||(C&&D)
            )
            .addRule(
                    0.4,
                    E,
                    V2// eg. (A&&B)||(C&&D)
            )
            .addRule(
                    V1,
                    V,T,V // eg. A&&B
            )
            .addRule(
                    V1,
                    F,V // eg. !A
            )
            .addRule(
                    V2,
                    P1, V,T,V, P2 // eg. (A&&B)
            )
            .addRule(
                    V2,
                    P1, V1,T,V, P2 // eg. (A&&B)
            )
            .addRule(
                    V2,
                    P1, V,T,V1, P2 // eg. (A&&B)
            )
            .addRule(
                    V2,
                    P1, V1,T,V1, P2 // eg. (A&&B)
            )
            .addRule(T, and)
            .addRule(T, or)
            .addRule(P1, leftP)
            .addRule(P2, rightP)
            .addRule(V, variable)
            .addRule(F, not) // eg. with a stick
            .build();

    public static void main(String[] args) {
        Parser<String> parser = new Parser<>(grammar);
//        System.out.println(
//                parser.recognize(S, Tokens.tokenize("(A&&B)")) // true
//        );
//        System.out.println(
//                parser.getViterbiParseWithScore(S, Tokens.tokenize("A","&&","B")) // Most likely parse tree with probability
//        );
        System.out.println(
                parser.getViterbiParseWithScore(S, Tokens.tokenize("( A && B )")) // Most likely parse tree with probability
        );
    }
}
