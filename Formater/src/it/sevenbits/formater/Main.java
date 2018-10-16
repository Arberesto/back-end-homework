package it.sevenbits.formater;

import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        //Formater formater = new Formater("({|})");
        Formater formater = new Formater("[{}]");
        StringBuilder sb = new StringBuilder();
        try(FileReader reader = new FileReader(args[0]))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                sb.append((char)(c));
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        if (sb.length() != 0) {
            String line = sb.toString();
            String result = formater.FormatLine(line);
            try(FileWriter writer = new FileWriter(args[0]+".fixed"))
            {
                writer.write(result);
                writer.flush();
            }
            catch(Exception ex){

                System.out.println(ex.getMessage());
            }
        }
    }
}
