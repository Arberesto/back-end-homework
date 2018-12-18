package it.sevenbits.formater.javaFormater.formater.container;

import it.sevenbits.formater.io.output.IWriter;
import it.sevenbits.formater.io.output.bufferedWriter.IBufferedWriter;

import java.io.IOException;

public interface IFormaterBufferContainer {

    void write() throws IOException;

    void writeTabulation() throws IOException;

    void setTabulationCount(int tabulationCount);

    int getTabulationCount();

    void setDestination(IBufferedWriter newWriter);

    void setNextString(final String nextString);
}
