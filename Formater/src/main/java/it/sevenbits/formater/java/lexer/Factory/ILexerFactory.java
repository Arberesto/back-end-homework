package it.sevenbits.formater.java.lexer.Factory;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.java.lexer.ILexer;

/**
 * Factory to create ILexer objects
 */

public interface ILexerFactory {

    /**
     * Create new Lexer object
     * @param fileReader object used to create Lexer object
     * @return new Lexer object
     */

    ILexer createLexer(IReader fileReader);
}
