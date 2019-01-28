package it.sevenbits.formater.javaFormater.lexer.token.symbolGroups;

public class SymbolGroups {

    public static final int letterUppercase = 1001;
    public static final int letterLowercase = 2001;
    public static final int digit = 4001;
    public static final int otherSymbols = 3001;
    public static final int space = (int) ' ';
    public static final int slash = (int) '/';
    public static final int star = (int) '*';
    public static final int endline = (int) '\n';
    public static final int singleQuotation = (int) "'".charAt(0);
    public static final int doubleQuotation = (int) '"';
    public static final int leftBrace = (int) '{';
    public static final int rightBrace  = (int) '}';

    public static int getSymbolID(final char symbol) {
        if (Character.isDigit(symbol)) {
            return digit;
        }

        if (Character.isLetter(symbol)) {
            if (Character.isLowerCase(symbol)) {
                return letterLowercase;
            } else {
                return letterUppercase;
            }
        }

        if (symbol == '"') {
            return doubleQuotation;
        }

        if (symbol == "'".charAt(0)) {
            return singleQuotation;
        }

        if (symbol == '\n') {
            return endline;
        }

        if (symbol == '/') {
            return slash;
        }

        if (symbol == '*') {
            return star;
        }

        if (symbol == ' ') {
            return space;
        }

        return otherSymbols;
    }
}
