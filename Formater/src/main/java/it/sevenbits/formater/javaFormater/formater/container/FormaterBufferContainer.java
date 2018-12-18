package it.sevenbits.formater.javaFormater.formater.container;

import it.sevenbits.formater.io.output.bufferedWriter.IBufferedWriter;

import java.io.IOException;

public class FormaterBufferContainer implements IFormaterBufferContainer {

    private IBufferedWriter writer;
    private final int spaceTab = 4;
    private int tabulationCount = 0;
    private String nextString;

    public FormaterBufferContainer() {}

    public void setDestination(final IBufferedWriter newWriter) {
        this.writer = newWriter;
    }

    public void write() throws IOException {
        writer.write(nextString);
    }

    public void setNextString(final String nextString) {
        this.nextString = nextString;
    }

    public void writeTabulation() throws IOException {
        for (int i = 0; i < spaceTab * tabulationCount; i++) {
            writer.write(' ');
        }
    }


    public void setTabulationCount(final int tabulationCount) {
        this.tabulationCount = tabulationCount;
    }

    public int getTabulationCount() {
        return this.tabulationCount;
    }
}
