package it.sevenbits.formater.javaFormater.lexer.container;

import it.sevenbits.formater.javaFormater.lexer.token.IToken;
import it.sevenbits.formater.javaFormater.lexer.token.Token;
import it.sevenbits.formater.javaFormater.lexer.token.tokenNames.StateMachineTokenName;
import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;

import java.util.HashMap;
import java.util.Map;

public class LexerBufferContainer implements ILexerBufferContainer {

    private char nextSymbol;
    private String name;
    private String nextName;
    private StringBuilder stringBuilder;
    private State currentState;
    private final StateMachineTokenName stateMachineTokenName;

    public LexerBufferContainer() {
        stringBuilder = new StringBuilder();
        name = "TOKEN_EMPTY";
        nextSymbol = '\0';
        currentState = new State("START");
        nextName = "TOKEN_EMPTY";
        stateMachineTokenName = new StateMachineTokenName();
    }

    public String getLexeme() {
        return stringBuilder.toString();
    }

    public void addSymbol() {
        stringBuilder.append(nextSymbol);
    }

    public void setNextSymbol(final char symbol) {
        this.nextSymbol = symbol;
    }

    public void clear() {
        stringBuilder = new StringBuilder();
        name = "TOKEN_EMPTY";
        nextSymbol = '\0';
        nextName = "TOKEN_EMPTY";
        currentState = new State("START");
    }

    public void rename() {
        this.name = stateMachineTokenName.getOrDefault(currentState, nextSymbol, this.name);
    }

    public IToken getToken() {
        return new Token(getName(), getLexeme());
    }

    public String getName() {
        return this.name;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setNextName(final String nextName) {
        this.nextName = nextName;
    }
}
