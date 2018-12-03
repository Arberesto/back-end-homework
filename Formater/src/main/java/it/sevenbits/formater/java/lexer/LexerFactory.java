package it.sevenbits.formater.java.lexer;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.io.input.fileReader.FileReader;

/**
 * Factory to create ILexer objects
 */

public class LexerFactory implements ILexerFactory {

    /**
     * Create new Lexer object
     * @param fileReader object used to create Lexer object
     * @return new Lexer object
     */

    public ILexer createLexer(final IReader fileReader) {
            return new Lexer((FileReader) fileReader);
    }
}
