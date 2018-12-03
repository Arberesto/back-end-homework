package it.sevenbits.formater.java.lexer;

import it.sevenbits.formater.java.token.IToken;
import java.io.IOException;

/**
 * interface to read tokens from stream
 */

public interface ILexer extends AutoCloseable {
    /**
     * Return next token readed from stream
     * @return IToken object
     * @throws IOException if error occurred in FileReader
     */
   IToken getNextToken() throws IOException;

    /**
     * Check if lexer has another token to get
     * @return true if has next token, else return false
     * @throws IOException if error occurred in FileReader
     */

   boolean hasNextToken() throws IOException;

    /**
     * Automatically calls when closing this object
     * @throws IOException if close is not supported
     * @see AutoCloseable
     */
   void close() throws IOException;
}
