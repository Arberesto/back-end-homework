package it.sevenbits.formater.IO.Output.StringWriter;

import java.io.FileWriter;
import java.io.IOException;

public class StringWriter implements IStringWriter, AutoCloseable {
    private FileWriter writer;
    public StringWriter(final String filename) throws IOException {
        writer = new FileWriter(filename);
    }

    public void write(final char nextSymbol) throws IOException {
        writer.append(nextSymbol);
        writer.flush();
    }

    public void close() {

    }
}
