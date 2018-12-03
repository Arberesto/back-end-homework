package it.sevenbits.formater.java.lexer.Factory;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.java.lexer.ILexer;
import it.sevenbits.formater.java.lexer.Lexer;

import java.io.IOException;

/**
 * Factory to create ILexer objects
 */

public class LexerFactory implements ILexerFactory {

    /**
     * Create new Lexer object
     * @param reader object used to create Lexer object
     * @return new Lexer object
     * @throws IOException if reach end of stream
     */

    public ILexer createLexer(final IReader reader) throws IOException {
            return new Lexer(reader);
    }
}
