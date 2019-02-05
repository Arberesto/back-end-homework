package it.sevenbits.formater.javaFormater.lexer.container;

import it.sevenbits.formater.javaFormater.lexer.token.IToken;
import it.sevenbits.formater.javaFormater.stateMachine.State;

public interface ILexerBufferContainer {

    String getLexeme();

    String getName();

    IToken getToken();

    void clear();

    void rename();

//    void setNextName(String nextName);

    void setCurrentState(State currentState);

//    void setName(String name);

    void addSymbol();

    void setNextSymbol(char symbol);
}
