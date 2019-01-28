package it.sevenbits.formater.javaFormater.lexer.token.tokenNames;

import it.sevenbits.formater.javaFormater.lexer.token.symbolGroups.SymbolGroups;
import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;

import java.util.HashMap;
import java.util.Map;

public class StateMachineTokenName {

    private final Map<Pair<Integer, State>, String> states;
    private final String spaceToken = "TOKEN_SPACE";
    private final String lineCommentaryToken = "TOKEN_LINE_COMMENTARY";
    private final String stringLiteralToken = "TOKEN_STRING_LITERAL";
    private final String literalToken = "TOKEN_LITERAL";
    private final String numberToken = "TOKEN_NUMBER";
    private final String endlineToken = "TOKEN_ENDLINE";
    private final String charLiteralToken = "TOKEN_CHAR_LITERAL";
    private final String otherSymbolsToken = "TOKEN_OTHER";
    private final String starToken = "TOKEN_STAR";
    private final String emptyToken = "TOKEN_EMPTY";

    private final State startState = new State("START");
    private final State endState = new State("END");
    private final State errorState = new State("ERROR");
    private final State lineCommentaryState = new State("ONE_LINE_COMMENTARY");
    private final State stringLiteralState = new State("STRING_LITERAL");
    private final State charLiteralStartState = new State("CHAR_LITERAL_START");
    private final State charLiteralEndState = new State("CHAR_LITERAL_END");
    private final State spaceState = new State("SPACE");
    private final State literalState = new State("LITERAL");
    private final State operationSymbolState = new State("OPERATION_SYMBOL");
    private final State numberState = new State("NUMBER");
    private final State commentSuspicionState = new State("COMMENT_SUSPICION");


    public StateMachineTokenName() {
        states = new HashMap<>();
        states.put(new Pair<>(SymbolGroups.space, startState), spaceToken);
        states.put(new Pair<>(SymbolGroups.letterLowercase, startState), literalToken);
        states.put(new Pair<>(SymbolGroups.letterUppercase, startState), literalToken);
        states.put(new Pair<>(SymbolGroups.otherSymbols, startState), otherSymbolsToken);
        states.put(new Pair<>(SymbolGroups.star, startState), starToken);
        states.put(new Pair<>(SymbolGroups.endline, startState), endlineToken);
        states.put(new Pair<>(SymbolGroups.singleQuotation, startState), charLiteralToken);
        states.put(new Pair<>(SymbolGroups.doubleQuotation, startState), stringLiteralToken);
        states.put(new Pair<>(SymbolGroups.digit, startState), numberToken);

        states.put(new Pair<>(SymbolGroups.space, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.letterLowercase, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.letterUppercase, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.otherSymbols, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.slash, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.star, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.endline, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.doubleQuotation, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.digit, charLiteralEndState), emptyToken);

        states.put(new Pair<>(SymbolGroups.endline, stringLiteralState), emptyToken);

        states.put(new Pair<>(SymbolGroups.endline, literalState), emptyToken);
        states.put(new Pair<>(SymbolGroups.singleQuotation, literalState), emptyToken);
        states.put(new Pair<>(SymbolGroups.doubleQuotation, literalState), emptyToken);

    }

    public String getOrDefault(final State currentState, final char nextSymbol, final String defaultString) {
        return states.getOrDefault(new Pair<>(SymbolGroups.getSymbolID(nextSymbol), currentState), defaultString);
    }

}
