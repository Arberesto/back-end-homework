package it.sevenbits.formater;

import it.sevenbits.formater.io.input.fileReader.FileReader;
import it.sevenbits.formater.io.output.fileWriter.FileWriter;
//import it.sevenbits.formater.javaFormater.formater.JavaFormater;
import it.sevenbits.formater.javaFormater.formater.StateMachineJavaFormater;
import it.sevenbits.formater.javaFormater.lexer.factory.LexerFactory;

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
        StateMachineJavaFormater javaStateMachineFormater = new StateMachineJavaFormater(new LexerFactory());
        try (FileReader fileReader = new FileReader(args[0])) {
                try (FileWriter fileWriter = new FileWriter(args[1])) {
                    try {
                        javaStateMachineFormater.format(fileReader, fileWriter);
                    } catch (IOException e) {
                        System.out.println("Sorry, can't format this");
                    }
                } catch (IOException e) {
                    System.out.println("Wrong output file or filepath");
                }
        } catch (IOException e) {
            System.out.println("Wrong input file or filepath");
        }
    }
}
