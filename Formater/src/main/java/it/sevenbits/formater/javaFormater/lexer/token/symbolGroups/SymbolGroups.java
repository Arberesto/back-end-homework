package it.sevenbits.formater.javaFormater.lexer.token.symbolGroups;

/**
 * Class contains codes of all symbols that can be detected by Lexer
 */

public final class SymbolGroups {
    // TODO: change list of static variables into map
    public static final int LETTER_UPPERCASE = 1001;
    public static final int LETTER_LOWERCASE = 2001;
    public static final int DIGIT = 4001;
    public static final int OTHER_SYMBOLS = 3001;
    public static final int SPACE = (int) ' ';
    public static final int DOT = (int) '.';
    public static final int SEMICOLON = (int) ';';
    public static final int SLASH = (int) '/';
    public static final int STAR = (int) '*';
    public static final int ENDLINE = (int) '\n';
    public static final int SINGLE_QUOTATION = 39;
    public static final int DOUBLE_QUOTATION = (int) '"';
    public static final int LEFT_BRACE = (int) '{';
    public static final int RIGHT_BRACE = (int) '}';

    private SymbolGroups(){}

    public static int getSymbolID(final char symbol) {
        if (Character.isDigit(symbol)) {
            return DIGIT;
        }

        if (Character.isLetter(symbol)) {
            if (Character.isLowerCase(symbol)) {
                return LETTER_LOWERCASE;
            } else {
                return LETTER_UPPERCASE;
            }
        }

        switch (symbol) {
            case '"':
                return DOUBLE_QUOTATION;

            case (char) SINGLE_QUOTATION:
                return SINGLE_QUOTATION;

            case '\n':
                return ENDLINE;

            case '/':
                return SLASH;

            case '*':
                return STAR;

            case '.':
                return DOT;

            case ';':
                return SEMICOLON;

            case ' ':
                return SPACE;

            case '{':
                return LEFT_BRACE;

            case '}':
                return RIGHT_BRACE;

            default:
                return OTHER_SYMBOLS;
        }

    }
}
