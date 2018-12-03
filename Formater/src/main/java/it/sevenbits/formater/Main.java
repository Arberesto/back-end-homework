package it.sevenbits.formater;

import it.sevenbits.formater.io.input.fileReader.FileReader;
import it.sevenbits.formater.io.output.fileWriter.FileWriter;
import it.sevenbits.formater.java.JavaFormater;
import it.sevenbits.formater.java.lexer.LexerFactory;

import java.io.IOException;

/**
 * Entry point for program
 */

final class Main {

    private Main() {
    }

    /**
     * Starting method
     *
     * @param args - arguments in command line(args[0] - path to file to format,
     *             args[1] - path to file to write formated text)
     */

    public static void main(final String[] args) {
        JavaFormater javaFormater = new JavaFormater(new LexerFactory());
        try (FileReader fileReader = new FileReader(args[0])) {
                try (FileWriter fileWriter = new FileWriter(args[1])) {
                    javaFormater.format(fileReader, fileWriter);
                } catch (IOException e) {
                    System.out.println("Write exception: \n" + e.getMessage());
                }
        } catch (IOException e) {
            System.out.println("Read exception: \n" + e.getMessage());
        }
    }
}
