package it.sevenbits.formater.IO.Output;

import java.io.IOException;

public interface IWriter {
    void write(char nextSymbol) throws IOException;
}
