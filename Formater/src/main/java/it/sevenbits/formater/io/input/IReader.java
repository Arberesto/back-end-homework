package it.sevenbits.formater.io.input;

import java.io.IOException;

/**
 *  interface for reading symbols from a stream
 */

public interface IReader extends AutoCloseable {

    /**
     * check if source has something else to read
     * @return true if has, else return false
     */
    boolean hasNext();

    /**
     * read single character from a stream
     * @return part of information that has been readed
     * @throws IOException if can't read from source
     */

    int read() throws IOException;

    /**
     * Automatically calls when closing this object
     * @throws IOException if close is not supported
     * @see AutoCloseable
     */

    void close() throws IOException;
}
