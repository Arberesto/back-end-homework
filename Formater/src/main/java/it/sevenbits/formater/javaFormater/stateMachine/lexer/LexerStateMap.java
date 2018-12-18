package it.sevenbits.formater.javaFormater.stateMachine.lexer;

import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;

import java.util.HashMap;
import java.util.Map;

public class LexerStateMap {
    private final State defaultState = new State("LITERAL");
    private final State startState = new State("START");
    private final State endState = new State("END");
    private final Map<Pair<State, Character>, State> states;

    LexerStateMap() {
        states = new HashMap<>();
        State lineCommentaryState = new State("ONE_LINE_COMMENTARY");
        State stringLiteralState = new State("STRING_LITERAL");
        State charLiteralState = new State("CHAR_LITERAL");
        State spaceState = new State("SPACE");
        State symbolState = new State("OPERATION_SYMBOL");
        State numberState = new State("NUMBER");
        State commentSuspicionState = new State("COMMENT_SUSPICION");

        states.put(new Pair<>(startState, '"'), stringLiteralState);
        states.put(new Pair<>(startState, "'".charAt(0)), charLiteralState);
        states.put(new Pair<>(startState, ' '), spaceState);
        states.put(new Pair<>(startState, '{'), endState);
        states.put(new Pair<>(startState, '}'), endState);
        states.put(new Pair<>(startState, '.'), endState);
        states.put(new Pair<>(startState, '/'), commentSuspicionState);

        states.put(new Pair<>(commentSuspicionState, '/'), lineCommentaryState);

        states.put(new Pair<>(lineCommentaryState, '\n'), endState);

    }

    public State getStartState() {
        return startState;
    }

    public State getEndState() {
        return endState;
    }

    public State getNextState(final State state, final char signal) {
        return states.getOrDefault(new Pair<>(state, signal), defaultState);
    }
}
