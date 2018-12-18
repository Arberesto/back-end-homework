package it.sevenbits.formater.javaFormater.lexer.factory;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.javaFormater.lexer.ILexer;
import it.sevenbits.formater.javaFormater.lexer.Lexer;
import it.sevenbits.formater.javaFormater.lexer.StateMachineLexer;

import java.io.IOException;

/**
 * factory to create ILexer objects
 */

public class LexerFactory implements ILexerFactory {

    /**
     * Create new Lexer object
     * @param reader object used to create Lexer object
     * @return new Lexer object
     * @throws IOException if reach end of stream
     */

    public ILexer createLexer(final IReader reader) throws IOException {
            return new StateMachineLexer(reader);
    }
}
