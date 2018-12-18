package it.sevenbits.formater.io.output.bufferedWriter;

import it.sevenbits.formater.io.output.IWriter;

import java.io.IOException;

/**
 *
 */

public class BufferedWriter implements IBufferedWriter {
    private IWriter writer;

    /**
     * assign writer object to real object
     * @param writer real writer object
     */

    public BufferedWriter(final IWriter writer) {
        this.writer = writer;
    }

    /**
     * write single character in a stream
     * @param nextSymbol symbol to write
     * @throws IOException throw if can't correctly write in destination
     */
    public void write(final char nextSymbol) throws IOException {
        writer.write(nextSymbol);
    }

    /**
     * write string in a stream
     * @param nextString string to write
     * @throws IOException throw if can't correctly write in destination
     */
    public void write(final String nextString) throws IOException {
        for (int i = 0; i < nextString.length(); i++) {
            writer.write(nextString.charAt(i));
        }
    }

    /**
     * Automatically calls when closing this object
     * @throws IOException if close is not supported
     * @see AutoCloseable
     */

    public void close() throws IOException {
        writer.close();
    }
}
