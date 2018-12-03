package it.sevenbits.formater.java.token;

public class Token implements IToken {
    private String name;
    private String lexeme;

    public Token(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    public Token() {
        this.name = "EMPTY_TOKEN";
        this.lexeme = "";
    }

    public String getName() {
        return name;
    }

    public String getLexeme() {
        return lexeme;
    }
}
