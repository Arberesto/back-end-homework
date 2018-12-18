package it.sevenbits.formater.javaFormater.stateMachine.lexer;

import it.sevenbits.formater.javaFormater.stateMachine.State;

public class LexerStateTransition {
    private final LexerStateMap lexerStateMap;

    public LexerStateTransition() {
        this.lexerStateMap = new LexerStateMap();
    }

    public State nextState(final State state, final char symbol) {
        return lexerStateMap.getNextState(state, symbol);
    }

    public State getStartState() {
        return this.lexerStateMap.getStartState();
    }

    public State getEndState() {
        return this.lexerStateMap.getEndState();
    }
}
