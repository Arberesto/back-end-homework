package it.sevenbits.formater.java;

import it.sevenbits.formater.IO.Input.IReader;
import it.sevenbits.formater.IO.Output.IWriter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaFormater {
    private static final int HAS_OPENED_BRACE = 1; // 1 or more if has opened brace before(number of braces)
    private static final int IS_PREVIOUS_SPACE = 2; //1 if previous symbol is space
    private static final int IS_PREVIOUS_NEWLINE = 3; //if previous symbol is \n
    private static final int HAS_NON_DIVIDER_BEFORE = 4; //if string after last opened brace contain non-[ \n\t{}] symbols
    private int[] flags = new int[]{0, 0, 0, 0, 0};

    public JavaFormater(){}

    public void format(final IReader iReader, final IWriter iWriter) throws IOException {
        StringBuilder sb = new StringBuilder();
        resetFlags();
        while (iReader.hasNext()) {
            char symbol = iReader.read();
            sb.append(replaceSymbol(symbol));
            changeFlags(symbol);
        }
        String sbString = sb.toString();
        for (int i = 0 ; i < sbString.length(); i++) {
            iWriter.write(sbString.charAt(i));
        }
    }

    private void resetFlags() {
        for (int i = 0; i < flags.length; i++) {
            flags[i] = 0;
        }
    }

    private void changeFlags(final char symbol) {
        String stringSymbol = "" + symbol;
        switch (symbol) {
            case '{':
                flags[HAS_OPENED_BRACE] += 1;
                flags[IS_PREVIOUS_SPACE] += 1;
                break;
            case '}':
                flags[HAS_OPENED_BRACE] -= 1;
                break;
            case ' ':
                flags[IS_PREVIOUS_SPACE] += 1;
                break;
            case '\n':
                flags[IS_PREVIOUS_NEWLINE] += 1;
                break;
            default:
                break;
        }
        if (Pattern.matches("^[\n\t {}]", stringSymbol)) {
            flags[HAS_NON_DIVIDER_BEFORE] += 1;
        }
    }

    String replaceSymbol(final char symbol) {
        StringBuilder sb = new StringBuilder();
        sb.append(symbol);
        return sb.toString();
    }

    String formatLine(final String line) {
        String result1 = findAndReplaceInText("([{])[ ]*[\n]?", line, "$1\n");
        //
        String result2 = findAndReplaceInText("(;)([ ]*)[\n]?", result1, "$1\n");
        //
        String result3 = findAndReplaceInText("[ ]*[\n]?([}])", result2, "\n$1");
        //
        String result4 = findAndReplaceInText("([}])[ ]*[\n]?[ ]*", result3, "$1\n");
        //
        String result5 = findAndReplaceInText("(\n)\t?(\n|[ ]*)", result4, "$1");
        //String result6 = findAndReplaceInText("{\n([.]*)\n}",result5,"$1");
        return findAndReplaceInText("[{][ ]*\n[ ]*[}]", makeTabulations(result5), "{}");
    }

    private String findAndReplaceInText(final String regularExpression, final String line, final String replacement) {
        Pattern patternOfCode = Pattern.compile(regularExpression);
        Matcher matcher = patternOfCode.matcher(line);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, replacement);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private String makeTabulations(final String line) {
        StringBuilder buffer = new StringBuilder(); // StringBuffer();
        Pattern pattern = Pattern.compile("\n");
        String [] pieces = pattern.split(line);
        int spaceCounter = 0;
        String spaceString = "    ";
        for (int i = 0; i < pieces.length - 1; i++) {
            if (Pattern.matches(".*[}]", pieces[i]) && (spaceCounter > 0)) {
                spaceCounter -= 1;
            }
            for (int j = 0; j < spaceCounter; j++) {
                pieces[i] = spaceString.concat(pieces[i]);
            }
            if (Pattern.matches(".*[{]", pieces[i])) {
                spaceCounter += 1;
            }
            buffer.append(pieces[i]).append("\n");
        }
        if (Pattern.matches(".*[}]", pieces[pieces.length - 1]) && (spaceCounter > 0)) {
            spaceCounter -= 1;
        }
        for (int j = 0; j < spaceCounter; j++) {
            pieces[pieces.length - 1] = spaceString.concat(pieces[pieces.length - 1]);
        }
        return buffer.toString();
    }
}
