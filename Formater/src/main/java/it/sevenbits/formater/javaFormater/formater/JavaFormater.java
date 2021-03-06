package it.sevenbits.formater.javaFormater.formater;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.io.output.bufferedWriter.BufferedWriter;
import it.sevenbits.formater.io.output.IWriter;
import it.sevenbits.formater.io.output.bufferedWriter.IBufferedWriter;
import it.sevenbits.formater.javaFormater.lexer.ILexer;
import it.sevenbits.formater.javaFormater.lexer.factory.ILexerFactory;
import it.sevenbits.formater.javaFormater.lexer.token.IToken;

import java.io.IOException;

/**
 * Format valid Java code from stream
 */

public class JavaFormater implements IFormater {
    private static final int HAS_OPENED_BRACE = 2; // 1 or more if has opened brace before(number of braces)
    private static final int IS_PREVIOUS_NEWLINE = 3; //if previous symbol is \n
    private static final int HAS_NON_DIVIDER_BEFORE = 4; //if string contain non-[ \n\t{}] symbols
    private static final int EXPECTED_NEWLINE = 5; //if previous symbol is \t, {, },
    private int[] flags = new int[]{0, 0, 0, 0, 0, 0};
    private ILexerFactory lexerFactory;
    private char previousSymbol;

    /**
     * Assign  formater's LexerFactory with actual object
     *
     * @param lexerFactory object to bond with LexerFactory of formater
     */

    public JavaFormater(final ILexerFactory lexerFactory) {
        this.lexerFactory = lexerFactory;
    }

    /**
     * Get text and format it(spaces, newlines, etc)
     *
     * @param reader - Reader object to read text from stream
     * @param writer - Writer object to write formated text in stream
     * @throws IOException if occurred 'reader' or 'writer' error
     */

    public void format(final IReader reader, final IWriter writer) throws IOException {
        IBufferedWriter bufferedWriter = new BufferedWriter(writer);
        ILexer lexer = this.lexerFactory.createLexer(reader);
            while (lexer.hasNextToken()) {
                final int spaceTab = 4;
                IToken token = lexer.getNextToken();
                switch (token.getName()) {
                    case "TOKEN_LEFTBRACE":
                        flags[IS_PREVIOUS_NEWLINE] = 0;
                        if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                            bufferedWriter.write('\n');
                            flags[IS_PREVIOUS_NEWLINE] += 1;
                        }
                        flags[EXPECTED_NEWLINE] = 1;
                        if (flags[HAS_NON_DIVIDER_BEFORE] != 0) {
                            if (previousSymbol != ' ') {
                                bufferedWriter.write(' ');
                            }
                        } else {
                            for (int i = 0; i < spaceTab * flags[HAS_OPENED_BRACE]; i++) {
                                bufferedWriter.write(' ');
                            }
                        }
                        bufferedWriter.write(token.getLexeme());
                        flags[HAS_OPENED_BRACE] += 1;
                        flags[HAS_NON_DIVIDER_BEFORE] = 0;
                        flags[EXPECTED_NEWLINE] = 1;
                        break;
                    case "TOKEN_RIGHTBRACE":
                        flags[IS_PREVIOUS_NEWLINE] = 0;
                        if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                            bufferedWriter.write('\n');
                            flags[IS_PREVIOUS_NEWLINE] += 1;
                        }
                        flags[HAS_OPENED_BRACE] -= 1;
                        if (flags[HAS_NON_DIVIDER_BEFORE] == 0) {
                            for (int i = 0; i < spaceTab * flags[HAS_OPENED_BRACE]; i++) {
                                bufferedWriter.write(' ');
                            }
                        }
                        bufferedWriter.write(token.getLexeme());
                        flags[EXPECTED_NEWLINE] = 1;
                        flags[HAS_NON_DIVIDER_BEFORE] = 0;
                        break;
                    case "TOKEN_SPACE":
                        if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                            bufferedWriter.write('\n');
                            flags[IS_PREVIOUS_NEWLINE] += 1;
                        }
                        if (flags[HAS_NON_DIVIDER_BEFORE] != 0) {
                            bufferedWriter.write(token.getLexeme());
                        }
                        break;
                    case "TOKEN_NEWLINE":
                        if (flags[IS_PREVIOUS_NEWLINE] > 1) {
                            flags[IS_PREVIOUS_NEWLINE] = 2;
                        } else {
                            bufferedWriter.write(token.getLexeme());
                            flags[IS_PREVIOUS_NEWLINE] += 1;
                            flags[EXPECTED_NEWLINE] = 0;
                        }
                        break;
                    case "TOKEN_TABULATION":
                        if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                            bufferedWriter.write('\n');
                            flags[IS_PREVIOUS_NEWLINE] += 1;
                        }
                        if (flags[HAS_NON_DIVIDER_BEFORE] != 0) {
                            bufferedWriter.write(token.getLexeme());
                        }
                        break;
                    case "TOKEN_SEMICOLON":
                        bufferedWriter.write(token.getLexeme());
                        flags[HAS_NON_DIVIDER_BEFORE] = 0;
                        flags[EXPECTED_NEWLINE] = 1;
                        flags[IS_PREVIOUS_NEWLINE] = 0;
                        break;
                    case "TOKEN_NON-DIVIDER":
                        flags[IS_PREVIOUS_NEWLINE] = 0;
                        if (flags[HAS_NON_DIVIDER_BEFORE] == 0) {
                            if ((previousSymbol == ';') || (previousSymbol == '{') || (previousSymbol == '}')) {
                                bufferedWriter.write('\n');
                                flags[IS_PREVIOUS_NEWLINE] += 1;
                            }
                            for (int i = 0; i < spaceTab * flags[HAS_OPENED_BRACE]; i++) {
                                bufferedWriter.write(' ');
                            }
                        }
                        flags[HAS_NON_DIVIDER_BEFORE] += 1;
                        bufferedWriter.write(token.getLexeme());
                        break;
                    default:
                        break;
                }
                previousSymbol = token.getLexeme().charAt(0);
            }
    }
}
