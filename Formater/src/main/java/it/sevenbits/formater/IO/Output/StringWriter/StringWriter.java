package it.sevenbits.formater.IO.Output.StringWriter;

/**
 * <pre>
 * Write in string per symbol
 * </pre>
 * @see it.sevenbits.formater.IO.Output.StringWriter.IStringWriter
 * @since             1.0
 */

public class StringWriter implements IStringWriter {
    private StringBuilder sb;

    /**
     * Constructor for StringWriter
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
}
