package it.sevenbits.formater.io.input.fileReader;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Class to read symbol from file stream
 */

public class FileReader implements IFileReader {

    private BufferedReader bufferedReader;
    private int nextChar;

    /**
     *  open BufferedReader to read from file
     * @param file file Object to read from
     * @throws IOException if can't read from file or another inner IO problem
     */

    public FileReader(final File file) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        nextChar = bufferedReader.read();
    }

    /**
     *  open BufferedReader to read from file
     * @param filename path to file to read from
     * @throws IOException if can't read from file or another inner IO problem
     */

    public FileReader(final String filename) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename),
                StandardCharsets.UTF_8));
        nextChar = bufferedReader.read();
    }

    /**
     * check if file has another symbol to read
     * @return true if has next symbol, else return false
     */

    public boolean hasNext() {
        return nextChar != -1;
    }

    /**
     * read symbol from file
     * @return code of symbol
     * @throws IOException if try to read when EOF reached
     */

    public int read() throws IOException {
        if (hasNext()) {
            int result = nextChar;
            try {
                nextChar = bufferedReader.read();
            } catch (IOException e) {
                nextChar = -1;
            }
            return result;
        }
        throw new IOException("EOF reached");
    }

    /**
     * Automatically calls when closing this object
     * @throws IOException if close is not supported
     * @see AutoCloseable
     */

    public void close() throws IOException {
        bufferedReader.close();
    }
}
