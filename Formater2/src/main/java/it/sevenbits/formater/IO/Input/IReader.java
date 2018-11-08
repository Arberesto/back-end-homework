package it.sevenbits.formater.IO.Input;

import java.io.IOException;

public interface IReader {
    boolean hasNext();
    char read() throws IOException;
}
