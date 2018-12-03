package it.sevenbits.formater.io.output.fileWriter;

import it.sevenbits.formater.io.output.IWriter;

import java.io.IOException;

/**
 * interface to write symbols into file stream
 */

interface IFileWriter extends IWriter {
    /**
     * write one symbol to file stream
     * @param nextSymbol symbol to write
     * @throws IOException if can't write into file or other inner IO problem
     */

    void write(char nextSymbol) throws IOException;
}
