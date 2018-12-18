package it.sevenbits.formater.io.output.bufferedWriter;

import it.sevenbits.formater.io.output.IWriter;

import java.io.IOException;

public interface IBufferedWriter extends IWriter {

    void write(char nextSymbol) throws IOException;

    /**
     * write string in a stream
     *
     * @param nextString string to write
     * @throws IOException throw if can't correctly write in destination
     */
    void write(String nextString) throws IOException;

    /**
     * Automatically calls when closing this object
     *
     * @throws IOException if close is not supported
     * @see AutoCloseable
     */

    void close() throws IOException;
}
