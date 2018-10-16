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
        return result4;
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
}
