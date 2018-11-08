package it.sevenbits.formater;

import it.sevenbits.formater.IO.Input.StringReader.StringReader;
import it.sevenbits.formater.IO.Output.StringWriter.StringWriter;
import it.sevenbits.formater.java.JavaFormater;

import java.io.IOException;


final class Main {
    private  Main(){}
    public static void main(final String[] args) {
        JavaFormater javaFormater = new JavaFormater();
        try (StringWriter writer = new StringWriter(args[0] + ".fixed"); StringReader reader = new StringReader(args[0])) {
            javaFormater.format(reader, writer);
        } catch (IOException e) {
            System.out.println("Exception: \n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unhandled exception: \n" + e.getMessage());
        }
    }
}
