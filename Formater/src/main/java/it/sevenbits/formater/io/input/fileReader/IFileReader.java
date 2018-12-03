package it.sevenbits.formater.io.input.fileReader;

import it.sevenbits.formater.io.input.IReader;
import java.io.IOException;

/**
 * Class for reading from file per symbol
 */

interface IFileReader extends IReader {

    /**
     * check if file has another symbol to read
     * @return true if has next symbol, else return false
     */

    boolean hasNext();

    /**
     * read symbol from file
     * @return code of symbol
     * @throws IOException if occurred file stream error
     */

    int read() throws IOException;
}
