package it.sevenbits.formater.java.token;

/**
 * Token that storage string lexeme
 */

public interface IToken {

    /**
     * Get name of this token
     * @return name
     */

    String getName();

    /**
     * Get lexeme of this token
     * @return lexeme
     */

    String getLexeme();

    @Override
    boolean equals(Object o);
}
