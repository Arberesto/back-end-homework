package it.sevenbits.formater.io.output.stringWriter;

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

    /**
     *
     * @return
     */

    public String toString() {
        return sb.toString();
    }

    /**
     * Automatically calls when closing this object
     * @see AutoCloseable
     */

    public void close() {
    }
}
