package it.sevenbits.formater.io.input.stringReader;


/**
 * <pre>
 * Read from string per symbol
 * </pre>
 * @see it.sevenbits.formater.io.input.stringReader.IStringReader
 * @since             1.0
 */

public class StringReader implements IStringReader {
    private String string;
    private int nextElementIndex;

    /**
     * Constructor for stringReader
     * @param string string to read from
     */

    public StringReader(final String string) {
        this.string = string;
        nextElementIndex = 0;
    }

    /**
     * check if string has next element to read
     * @return true - has element, false - reach end of string
     */

    public boolean hasNext() {
        return nextElementIndex < string.length();
    }

    /**
     * Read one symbol
     * @return ascii code of symbol(-1 if read nothing)
     */

    public int read() {
        if (hasNext()) {
            nextElementIndex++;
            return (int) string.charAt(nextElementIndex - 1);
        }
        return -1;
    }

    /**
     * Automatically calls when closing this object
     * @see AutoCloseable
     */

    public void close() {}
}
