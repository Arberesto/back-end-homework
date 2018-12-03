package it.sevenbits.formater.java.token;

/**
 * Token that storage string lexeme
 */

public class Token implements IToken {
    private String name;
    private String lexeme;

    /**
     * Create new token
     * @param name name for token
     * @param lexeme lexeme for token
     */

    public Token(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    /**
     * Create new empty token
     */

    public Token() {
        this.name = "TOKEN_EMPTY";
        this.lexeme = " ";
    }

    public String getName() {
        return name;
    }

    public String getLexeme() {
        return lexeme;
    }

    @Override
    public boolean equals(final Object o) {
        if ((o.getClass() != IToken.class) && (o.getClass() != Token.class)) {
            return false;
        }
        IToken other = (IToken) o;
        return ((this.getName().equals(other.getName())) && (this.getLexeme().equals(other.getLexeme())));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
