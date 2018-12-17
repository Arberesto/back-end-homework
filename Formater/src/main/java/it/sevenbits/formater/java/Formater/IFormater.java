package it.sevenbits.formater.java.Formater;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.io.output.IWriter;

import java.io.IOException;

/**
 * Format valid code from stream
 */

public interface IFormater {

    /**
     * Get text and format it(spaces, newlines, etc)
     * @param reader - Reader object to read text from stream
     * @param writer - Writer object to write formated text in stream
     * @throws IOException if occurred 'reader' or 'writer' error
     */

    void format(IReader reader, IWriter writer) throws IOException;
}
