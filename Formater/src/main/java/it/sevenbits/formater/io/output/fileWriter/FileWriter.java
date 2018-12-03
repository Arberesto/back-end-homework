package it.sevenbits.formater.io.output.fileWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
/**
 * class to read symbols from file stream
 */

public class FileWriter implements IFileWriter {
    private BufferedWriter bufferedWriter;

    /**
     *  open BufferedWriter to write into file
     * @param file file Object to write into
     * @throws IOException if can't write into file or another inner IO problem
     */

    public FileWriter(final File file) throws IOException {
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));

    }

    /**
     *  open BufferedWriter to write into file
     * @param filepath path to file to write into
     * @throws IOException if can't write into file or another inner IO problem
     */

    public FileWriter(final String filepath) throws IOException {
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath),
                StandardCharsets.UTF_8));

    }

    /**
     * write one symbol to file stream
     * @param nextSymbol symbol to write
     * @throws IOException if can't write into file or other inner IO problem
     */

    public void write(final char nextSymbol) throws IOException {
        bufferedWriter.write(nextSymbol);
        bufferedWriter.flush();
    }

    /**
     * Automatically calls when closing this object
     * @throws IOException if close is not supported
     * @see AutoCloseable
     */

    public void close() throws IOException {
        bufferedWriter.close();
    }
}
