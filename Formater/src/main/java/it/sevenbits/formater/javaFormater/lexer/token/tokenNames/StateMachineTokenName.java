package it.sevenbits.formater.javaFormater.lexer.token.tokenNames;

import it.sevenbits.formater.javaFormater.lexer.token.symbolGroups.SymbolGroups;
import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;

import java.util.HashMap;
import java.util.Map;

public class StateMachineTokenName {

    private final Map<Pair<Integer, State>, String> states;
    private final String spaceToken = "TOKEN_SPACE";
    private final String stringLiteralToken = "TOKEN_STRING_LITERAL";
    private final String literalToken = "TOKEN_LITERAL";
    private final String numberToken = "TOKEN_NUMBER";
    private final String endlineToken = "TOKEN_ENDLINE";
    private final String semicolonToken = "TOKEN_SEMICOLON";
    private final String charLiteralToken = "TOKEN_CHAR_LITERAL";
    private final String otherSymbolsToken = "TOKEN_OTHER";
    private final String starToken = "TOKEN_STAR";
    private final String emptyToken = "TOKEN_EMPTY";
    private final String leftBraceToken = "TOKEN_LEFTBRACE";
    private final String rightBraceToken = "TOKEN_RIGHTBRACE";

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
        states.put(new Pair<>(SymbolGroups.SPACE, startState), spaceToken);
        states.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, startState), literalToken);
        states.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, startState), literalToken);
        states.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, startState), otherSymbolsToken);
        states.put(new Pair<>(SymbolGroups.STAR, startState), starToken);
        states.put(new Pair<>(SymbolGroups.DOT, startState), emptyToken);
        states.put(new Pair<>(SymbolGroups.SEMICOLON, startState), semicolonToken);
        states.put(new Pair<>(SymbolGroups.ENDLINE, startState), endlineToken);
        states.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, startState), charLiteralToken);
        states.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, startState), stringLiteralToken);
        states.put(new Pair<>(SymbolGroups.DIGIT, startState), numberToken);
        states.put(new Pair<>(SymbolGroups.LEFT_BRACE, startState), leftBraceToken);
        states.put(new Pair<>(SymbolGroups.RIGHT_BRACE, startState), rightBraceToken);

        states.put(new Pair<>(SymbolGroups.SPACE, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.SLASH, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.STAR, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.DOT, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.SEMICOLON, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.ENDLINE, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.DIGIT, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.LEFT_BRACE, charLiteralEndState), emptyToken);
        states.put(new Pair<>(SymbolGroups.RIGHT_BRACE, charLiteralEndState), emptyToken);

        states.put(new Pair<>(SymbolGroups.ENDLINE, stringLiteralState), emptyToken);

        states.put(new Pair<>(SymbolGroups.ENDLINE, literalState), emptyToken);
        states.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, literalState), emptyToken);
        states.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, literalState), emptyToken);

    }

    public String getOrDefault(final State currentState, final char nextSymbol, final String defaultString) {
        return states.getOrDefault(new Pair<>(SymbolGroups.getSymbolID(nextSymbol), currentState), defaultString);
    }

}
