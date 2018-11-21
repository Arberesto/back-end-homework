package it.sevenbits.formater;

import it.sevenbits.formater.IO.Input.StringReader.StringReader;
import it.sevenbits.formater.IO.Output.StringWriter.StringWriter;
import it.sevenbits.formater.java.JavaFormater;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Entry point for program
 */

final class Main {

    private  Main(){}

    /**
     *Starting method
     * @param args - arguments in command line for app(args[0] - name for file to format)
     */

    public static void main(final String[] args) {
        JavaFormater javaFormater = new JavaFormater();
        StringBuilder sb = new StringBuilder();
        String formatedText = "";
        try (FileReader fileReader = new FileReader(args[0])) {
            int c;
            while ((c = fileReader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            System.out.println("Read exception: \n" + e.getMessage());
        }
//        catch (Exception e) {
//            System.out.println("Unhandled read exception: \n" + e.getMessage());
//        }

        try {
            StringWriter writer = new StringWriter();
            StringReader reader = new StringReader(sb.toString());
            javaFormater.format(reader, writer);
            formatedText = writer.toString();
        } catch (IOException e) {
            System.out.println("Formating exception: \n" + e.getMessage());
        }
//        catch (Exception e) {
//            System.out.println("Unhandled formating exception: \n" + e.getMessage());
//        }

        try (FileWriter fileWriter = new FileWriter(args[0] + ".fixed")) {
            fileWriter.append(formatedText);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Write exception: \n" + e.getMessage());
        }
//        catch (Exception e) {
//            System.out.println("Unhandled write exception: \n" + e.getMessage());
//        }
    }
}
