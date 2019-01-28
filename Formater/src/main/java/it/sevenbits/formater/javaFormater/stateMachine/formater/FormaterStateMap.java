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
        State lexemState = new State("LEXEM");
        State endOfLineState = new State("END_OF_LINE");
        State preEndState = new State("PRE_END");

        states.put(new Pair<>(startState, "TOKEN_SPACE"), startState);
        states.put(new Pair<>(startState, "TOKEN_ENDLINE"), endOfLineState);
        states.put(new Pair<>(startState, "TOKEN_LEFTBRACE"), preEndState);
        states.put(new Pair<>(startState, "TOKEN_RIGHTBRACE"), preEndState);
        states.put(new Pair<>(startState, "TOKEN_SEMICOLON"), endOfLineState);
        states.put(new Pair<>(startState, "TOKEN_SPECIAL_SYMBOL"), startState);
        states.put(new Pair<>(startState, "TOKEN_LITERAL"), lexemState);
        states.put(new Pair<>(startState, "TOKEN_OTHER"), startState);
        states.put(new Pair<>(startState, "TOKEN_STAR"), startState);
        states.put(new Pair<>(startState, "TOKEN_CHAR_LITERAL"), lexemState);
        states.put(new Pair<>(startState, "TOKEN_NUMBER"), lexemState);
        states.put(new Pair<>(startState, "TOKEN_STRING_LITERAL"), lexemState);
        states.put(new Pair<>(startState, "TOKEN_EMPTY"), finishState);

        states.put(new Pair<>(lexemState, "TOKEN_SPACE"), lexemState);
        states.put(new Pair<>(lexemState, "TOKEN_ENDLINE"), startState);
        states.put(new Pair<>(lexemState, "TOKEN_LEFTBRACE"), endOfLineState);
        states.put(new Pair<>(lexemState, "TOKEN_RIGHTBRACE"), endOfLineState);
        states.put(new Pair<>(lexemState, "TOKEN_SEMICOLON"), endOfLineState);
        states.put(new Pair<>(lexemState, "TOKEN_SPECIAL_SYMBOL"), lexemState);
        states.put(new Pair<>(lexemState, "TOKEN_LITERAL"), lexemState);
        states.put(new Pair<>(lexemState, "TOKEN_OTHER"), lexemState);
        states.put(new Pair<>(lexemState, "TOKEN_STAR"), lexemState);
        states.put(new Pair<>(lexemState, "TOKEN_CHAR_LITERAL"), lexemState);
        states.put(new Pair<>(lexemState, "TOKEN_NUMBER"), lexemState);
        states.put(new Pair<>(lexemState, "TOKEN_STRING_LITERAL"), lexemState);
        states.put(new Pair<>(lexemState, "TOKEN_EMPTY"), finishState);

    }

    public State getStartState() {
        return startState;
    }

    public State getFinishState() {
        return finishState;
    }

    public State getNextState(final State state, final String signal) {
        return states.getOrDefault(new Pair<>(state, signal), startState);
    }
}
