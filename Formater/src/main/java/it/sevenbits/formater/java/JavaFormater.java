package it.sevenbits.formater.java;

import it.sevenbits.formater.IO.Input.IReader;
import it.sevenbits.formater.IO.Output.IWriter;

import java.io.IOException;

/**
 *  Class can format valid Java code from string
 */

public class JavaFormater {
    private static final int HAS_OPENED_BRACE = 2; // 1 or more if has opened brace before(number of braces)
    private static final int IS_PREVIOUS_NEWLINE = 3; //if previous symbol is \n
    private static final int HAS_NON_DIVIDER_BEFORE = 4; //if string contain non-[ \n\t{}] symbols
    private static final int EXPECTED_NEWLINE = 5; //if previous symbol is \t, {, },
    private int[] flags = new int[]{0, 0, 0, 0, 0, 0};
    private char previousSymbol;

    /**
     * Get text and format it(spaces, newlines, etc)
     * @param reader - Reader object to read from destination file,stream,string,or any other available variant
     * @param writer - Writer object to write in destination file,stream,string,or any other available variant
     * @throws IOException if can't correctly read from source or write in destination
     */

    public void format(final IReader reader, final IWriter writer) throws IOException {
        char symbol;
        resetFlags();
        while ((symbol = (char) reader.read()) != (char) -1) {
            final int spaceTab = 4;
            switch (symbol) {
                case '{':
                    flags[IS_PREVIOUS_NEWLINE] = 0;
                    if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                        writer.write('\n');
                        flags[IS_PREVIOUS_NEWLINE] += 1;
                    }
                    flags[EXPECTED_NEWLINE] = 1;
                    if (flags[HAS_NON_DIVIDER_BEFORE] != 0) {
                        if (previousSymbol != ' ') {
                            writer.write(' ');
                        }
                    } else {
                        for (int i = 0; i < spaceTab * flags[HAS_OPENED_BRACE]; i++) {
                            writer.write(' ');
                        }
                    }
                    writer.write(symbol);
                    flags[HAS_OPENED_BRACE] += 1;
                    flags[HAS_NON_DIVIDER_BEFORE] = 0;
                    flags[EXPECTED_NEWLINE] = 1;
                    break;
                case '}':
                    flags[IS_PREVIOUS_NEWLINE] = 0;
                    if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                        writer.write('\n');
                        flags[IS_PREVIOUS_NEWLINE] += 1;
                    }
                    flags[HAS_OPENED_BRACE] -= 1;
                    if (flags[HAS_NON_DIVIDER_BEFORE] == 0) {
                        for (int i = 0; i < spaceTab * flags[HAS_OPENED_BRACE]; i++) {
                            writer.write(' ');
                        }
                    }
                    writer.write(symbol);
                    flags[EXPECTED_NEWLINE] = 1;
                    flags[HAS_NON_DIVIDER_BEFORE] = 0;
                    break;
                case ' ':
                    if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                        writer.write('\n');
                        flags[IS_PREVIOUS_NEWLINE] += 1;
                    }
                    if (flags[HAS_NON_DIVIDER_BEFORE] != 0) {
                        writer.write(symbol);
                    }
                    break;
                case '\n':
                    if (flags[IS_PREVIOUS_NEWLINE] > 1) {
                        flags[IS_PREVIOUS_NEWLINE] = 2;
                    } else {
                        writer.write(symbol);
                        flags[IS_PREVIOUS_NEWLINE] += 1;
                        flags[EXPECTED_NEWLINE] = 0;
                    }
                    break;
                case '\t':
                    if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                        writer.write('\n');
                        flags[IS_PREVIOUS_NEWLINE] += 1;
                    }
                    if (flags[HAS_NON_DIVIDER_BEFORE] != 0) {
                        writer.write(symbol);
                    }
                    break;
                case ';':
                    writer.write(symbol);
                    flags[HAS_NON_DIVIDER_BEFORE] = 0;
                    flags[EXPECTED_NEWLINE] = 1;
                    flags[IS_PREVIOUS_NEWLINE] = 0;
                    break;
                default:
                    flags[IS_PREVIOUS_NEWLINE] = 0;
                    if (flags[HAS_NON_DIVIDER_BEFORE] == 0) {
                        if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                            writer.write('\n');
                            flags[IS_PREVIOUS_NEWLINE] += 1;
                        }
                        for (int i = 0; i < spaceTab * flags[HAS_OPENED_BRACE]; i++) {
                            writer.write(' ');
                        }
                    }
                    flags[HAS_NON_DIVIDER_BEFORE] += 1;
                    writer.write(symbol);
                    break;
            }
            previousSymbol = symbol;
        }
    }

    private void resetFlags() {
        for (int i = 0; i < flags.length; i++) {
            flags[i] = 0;
        }
    }
}
