package it.sevenbits.formater.javaFormater.stateMachine.formater;

import it.sevenbits.formater.javaFormater.stateMachine.State;
import it.sevenbits.formater.javaFormater.lexer.token.IToken;

public final class FormaterStateTransition {

    private final FormaterStateMap formaterStateMap;

    public FormaterStateTransition() {
        this.formaterStateMap = new FormaterStateMap();
    }

    public State nextState(final State state, final IToken p) {
        return formaterStateMap.getNextState(state, p.getName());
    }

    public State getStartState() {
        return this.formaterStateMap.getStartState();
    }
}
