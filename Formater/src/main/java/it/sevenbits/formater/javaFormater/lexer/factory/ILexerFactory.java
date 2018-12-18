package it.sevenbits.formater.javaFormater.lexer.factory;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.javaFormater.lexer.ILexer;

import java.io.IOException;

/**
 * factory to create ILexer objects
 */

public interface ILexerFactory {

    /**
     * Create new Lexer object
     * @param fileReader object used to create Lexer object
     * @return new Lexer object
     * @throws IOException if reach end of stream
     */

    ILexer createLexer(IReader fileReader) throws IOException;
}
