package it.sevenbits.formater.io.output;

import java.io.IOException;

/**
 *  interface for writing symbols in a stream
 */

public interface IWriter extends AutoCloseable {
    /**
     * write single character in a stream
     * @param nextSymbol symbol to write
     * @throws IOException throw if can't correctly write in destination
     */
    void write(char nextSymbol) throws IOException;

    /**
     * Automatically calls when closing this object
     * @throws IOException if close is not supported
     * @see AutoCloseable
     */

    void close() throws IOException;
}
