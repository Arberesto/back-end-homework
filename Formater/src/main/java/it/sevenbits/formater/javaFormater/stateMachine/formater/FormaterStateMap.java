package it.sevenbits.formater.javaFormater.stateMachine.formater;

import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;

import java.util.HashMap;
import java.util.Map;

class FormaterStateMap {
    private final State finishState = new State("END");
    private final State startState = new State("START_OF_LINE");

    private final Map<Pair<State, String>, State> states;

    FormaterStateMap() {
        states = new HashMap<>();

        final String spaceToken = "TOKEN_SPACE";
        final String stringLiteralToken = "TOKEN_STRING_LITERAL";
        final String literalToken = "TOKEN_LITERAL";
        final String endlineToken = "TOKEN_ENDLINE";
        final String semicolonToken = "TOKEN_SEMICOLON";
        final String charLiteralToken = "TOKEN_CHAR_LITERAL";
        final String leftBraceToken = "TOKEN_LEFTBRACE";
        final String rightBraceToken = "TOKEN_RIGHTBRACE";

        State lexemState = new State("LEXEM");
        State endLineSuspicionState = new State("END_OF_LINE_SUSPICION");
        State lineCommentaryState = new State("LINE_COMMENTARY");

        states.put(new Pair<>(startState, leftBraceToken), endLineSuspicionState);
        states.put(new Pair<>(startState, rightBraceToken), endLineSuspicionState);
        states.put(new Pair<>(startState, semicolonToken), endLineSuspicionState);
        states.put(new Pair<>(startState, stringLiteralToken), lexemState);
        states.put(new Pair<>(startState, literalToken), lexemState);
        states.put(new Pair<>(startState, charLiteralToken), lexemState);

        states.put(new Pair<>(lexemState, leftBraceToken), endLineSuspicionState);
        states.put(new Pair<>(lexemState, rightBraceToken), endLineSuspicionState);
        states.put(new Pair<>(lexemState, semicolonToken), endLineSuspicionState);
        states.put(new Pair<>(lexemState, endlineToken), startState);

        states.put(new Pair<>(lineCommentaryState, endlineToken), startState);

        states.put(new Pair<>(endLineSuspicionState, endlineToken), startState);
        states.put(new Pair<>(endLineSuspicionState, stringLiteralToken), startState);
        states.put(new Pair<>(endLineSuspicionState, literalToken), startState);
        states.put(new Pair<>(endLineSuspicionState, charLiteralToken), startState);

    }

    public State getStartState() {
        return startState;
    }

    public State getFinishState() {
        return finishState;
    }

    public State getNextState(final State state, final String signal) {
        return states.getOrDefault(new Pair<>(state, signal), state);
    }
}
