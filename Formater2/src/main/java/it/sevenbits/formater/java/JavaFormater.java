package it.sevenbits.formater.java;

import it.sevenbits.formater.IO.Input.IReader;
import it.sevenbits.formater.IO.Output.IWriter;

import java.io.IOException;

public class JavaFormater {
    private static final int HAS_OPENED_BRACE = 2; // 1 or more if has opened brace before(number of braces)
    private static final int IS_PREVIOUS_NEWLINE = 3; //if previous symbol is \n
    private static final int HAS_NON_DIVIDER_BEFORE = 4; //if string contain non-[ \n\t{}] symbols
    private static final int EXPECTED_NEWLINE = 5; //if previous symbol is \t, {, },
    private int[] flags = new int[]{0, 0, 0, 0, 0, 0};
    private char previousSymbol;

    public JavaFormater() {
    }

    public void format(final IReader iReader, final IWriter iWriter) throws IOException {
        char symbol;
        resetFlags();
        while (iReader.hasNext()) {
            symbol = iReader.read();
            final int spaceTab = 4;
            switch (symbol) {
                case '{':
                    flags[EXPECTED_NEWLINE] = 1;
                    if (flags[HAS_NON_DIVIDER_BEFORE] != 0) {
                        if (previousSymbol != ' ') {
                            iWriter.write(' ');
                        }
                        flags[IS_PREVIOUS_NEWLINE] = 0;
                    }
                    iWriter.write(symbol);
                    flags[HAS_OPENED_BRACE] += 1;
                    flags[HAS_NON_DIVIDER_BEFORE] = 0;
                    flags[EXPECTED_NEWLINE] = 1;
                    break;
                case '}':
                    if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                        iWriter.write('\n');
                    }
                    flags[HAS_OPENED_BRACE] -= 1;
                    if (flags[HAS_NON_DIVIDER_BEFORE] == 0) {
                        for (int i = 0; i < spaceTab * flags[HAS_OPENED_BRACE]; i++) {
                            iWriter.write(' ');
                        }
                    }
                    iWriter.write(symbol);
                    flags[EXPECTED_NEWLINE] = 1;
                    flags[HAS_NON_DIVIDER_BEFORE] = 0;
                    break;
                case ' ':
                    if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                        iWriter.write('\n');
                    }
                    if (flags[HAS_NON_DIVIDER_BEFORE] != 0) {
                        iWriter.write(symbol);
                    }
                    break;
                case '\n':
                    if (flags[IS_PREVIOUS_NEWLINE] > 2) {
                        flags[IS_PREVIOUS_NEWLINE] = 2;
                    } else {
                        iWriter.write(symbol);
                        flags[EXPECTED_NEWLINE] = 0;
                    }
                    break;
                case '\t':
                    if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                        iWriter.write('\n');
                    }
                    if (flags[HAS_NON_DIVIDER_BEFORE] != 0) {
                        iWriter.write(symbol);
                    }
                    break;
                case ';':
                    iWriter.write(symbol);
                    flags[HAS_NON_DIVIDER_BEFORE] = 0;
                    flags[EXPECTED_NEWLINE] = 1;
                    break;
                default:
                    if (flags[HAS_NON_DIVIDER_BEFORE] == 0) {
                        if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                            iWriter.write('\n');
                        }
                        for (int i = 0; i < spaceTab * flags[HAS_OPENED_BRACE]; i++) {
                            iWriter.write(' ');
                        }
                    }
                    flags[HAS_NON_DIVIDER_BEFORE] += 1;
                    iWriter.write(symbol);
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
