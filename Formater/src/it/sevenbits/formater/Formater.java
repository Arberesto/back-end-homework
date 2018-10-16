package it.sevenbits.formater;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formater {
    private String regularExpression;

    private Formater(){}

    public Formater(String newRegularExpression) {
        regularExpression = newRegularExpression;
    }
    public String FormatLine(String line){
        String result1 = FindAndReplaceInText("([{])[ ]*[\n]?", line,"$1\n");
        //
        String result2 = FindAndReplaceInText("(;)([ ]*)[\n]?", result1,"$1\n");
        //
        String result3 = FindAndReplaceInText("[ ]*[\n]?([}])", result2,"\n$1");
        //
        String result4 = FindAndReplaceInText("([}])[ ]*[\n]?[ ]*", result3,"$1\n");
        //
        String result5 = FindAndReplaceInText("(\n)\t?(\n|[ ]*)",result4,"$1");
        //String result6 = FindAndReplaceInText("{\n([.]*)\n}",result5,"$1");
        return FindAndReplaceInText("[{][ ]*\n[ ]*[}]",MakeTabulations(result5),"{}");
    }

    public String FindAndReplaceInText(String regularExpression,String line,String replacement) {
        Pattern patternOfCode = Pattern.compile(regularExpression);
        Matcher matcher = patternOfCode.matcher(line);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, replacement);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    public String MakeTabulations(String line){
        StringBuffer buffer = new StringBuffer();
        Pattern pattern = Pattern.compile("\n");
        String [] pieces = pattern.split(line);
        int spaceCounter = 0;
        String spaceString = "    ";
        for(int i = 0; i < pieces.length; i++){
            for (int j = 0; j < spaceCounter; j++){
                pieces[i] = spaceString.concat(pieces[i]);
            }
            if (Pattern.matches(".*[{]",pieces[i])) {
                spaceCounter +=1;
            }
            if (Pattern.matches(".*[}]",pieces[i])&&(spaceCounter > 0)) {
                spaceCounter -=1;
            }
            buffer.append(pieces[i]).append("\n");
        }
        return buffer.toString();
    }
}
