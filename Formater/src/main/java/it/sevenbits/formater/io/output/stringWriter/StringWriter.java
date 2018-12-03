package it.sevenbits.formater.io.output.stringWriter;

import java.io.IOException;

/**
 * <pre>
 * Write in string per symbol
 * </pre>
 * @see it.sevenbits.formater.io.output.stringWriter.IStringWriter
 * @since             1.0
 */

public class StringWriter implements IStringWriter {
    private StringBuilder sb;

    /**
     * Constructor for stringWriter
     * @since             1.0
     */

    public StringWriter() {
        sb = new StringBuilder();
    }

    /**
     * write one symbol to string
     * @since             1.0
     * @param nextSymbol next symbol to write
     */

    public void write(final char nextSymbol) {
        sb.append(nextSymbol);
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    /**
     * Automatically calls when closing this object
     * @throws IOException if close is not supported
     * @see AutoCloseable
     */

    public void close() {
    }
}
