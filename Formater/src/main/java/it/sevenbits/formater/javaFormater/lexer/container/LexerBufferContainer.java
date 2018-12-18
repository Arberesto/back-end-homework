package it.sevenbits.formater.javaFormater.lexer.container;

import it.sevenbits.formater.javaFormater.lexer.token.IToken;
import it.sevenbits.formater.javaFormater.lexer.token.Token;

public class LexerBufferContainer implements ILexerBufferContainer {

    private char nextSymbol;
    private String name;
    private String nextName;
    private StringBuilder stringBuilder;

    public LexerBufferContainer() {
        stringBuilder = new StringBuilder();
        name = "TOKEN_EMPTY";
        nextSymbol = '\0';
        nextName = "TOKEN_EMPTY";
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
    }

    public void rename() {
        this.name = nextName;
    }

    public IToken getToken() {
        return new Token(getName(), getLexeme());
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setNextName(final String nextName) {
        this.nextName = nextName;
    }
}
