package it.sevenbits.formater.javaFormater.lexer.container;

import it.sevenbits.formater.javaFormater.lexer.token.IToken;

public interface ILexerBufferContainer {

    String getLexeme();

    String getName();

    IToken getToken();

    void clear();

    void rename();

    void setNextName(String nextName);

    void setName(String name);

    void addSymbol();

    void setNextSymbol(char symbol);
}
