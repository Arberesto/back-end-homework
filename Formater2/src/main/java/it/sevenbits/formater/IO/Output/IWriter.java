package it.sevenbits.formater.IO.Output;

import java.io.IOException;

/**
 *  can write per symbol in file,stream, string, etc
 */

public interface IWriter {
    /**
     *
     * @param nextSymbol symbol to write
     * @throws IOException throw if can't correctly write in destination
     */
    void write(char nextSymbol) throws IOException;
}
