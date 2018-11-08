package it.sevenbits.formater.IO.Input.StringReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StringReader implements IStringReader, AutoCloseable {
    private FileReader reader;
    private int nextElement;

    public StringReader(String filename) throws FileNotFoundException {
        reader = new FileReader(filename);
        try {
            nextElement = reader.read();
        } catch (IOException e) {
            System.out.println("Error! Empty file");
        }
    }

    public boolean hasNext() {
        return nextElement != -1;
    }

    public char read() throws IOException {
        char temp = (char) nextElement;
        nextElement = reader.read();
        return temp;
    }

    public void close() throws IOException {
        reader.close();
    }
}
