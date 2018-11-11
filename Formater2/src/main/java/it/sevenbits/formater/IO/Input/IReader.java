package it.sevenbits.formater.IO.Input;

import java.io.IOException;

/**
 *  can read per symbol from file,stream, string, etc
 */

public interface IReader {

    /**
     * check if source has something else to read
     * @return true if has, else return false
     */
    boolean hasNext();

    /**
     *Read one part of information from source
     * @return part of information that has been readed
     * @throws IOException if can't read from source
     */

    int read() throws IOException;
}
