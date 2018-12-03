package it.sevenbits.formater.java.lexer;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.java.token.IToken;
import it.sevenbits.formater.java.token.Token;
import java.io.IOException;

/**
 * Class for reading tokens from stream
 */

public class Lexer implements ILexer {

    private IReader fileReader;
    private IToken nextToken;
    /**
     * Bond its own fileReader with param1 object
     * @param fileReader object that bond with Lexer fileReader
     */

    Lexer(final IReader fileReader) {

        this.fileReader = fileReader;
        nextToken = new Token("TOKEN_EMPTY", "");
        try {
            this.nextToken = getNextToken();
        } catch (IOException e) {
            nextToken = new Token("TOKEN_EMPTY", "");
        }
    }

    /**
     * Return next token readed from FileReader
     * @return IToken object
     * @throws IOException if error occurred in FileReader
     */

    public IToken getNextToken() throws IOException {
        StringBuilder sb = new StringBuilder();
        IToken result;
        if (fileReader.hasNext()) {
            char symbol = (char) fileReader.read();
            switch (symbol) {
                case '{':
                    sb.append(symbol);
                    result = nextToken;
                    nextToken = new Token("TOKEN_LEFTBRACE", sb.toString());
                    return result;
                case '}':
                    sb.append(symbol);
                    result = nextToken;
                    nextToken = new Token("TOKEN_RIGHTBRACE", sb.toString());
                    return result;
                case ' ':
                    sb.append(symbol);
                    result = nextToken;
                    nextToken = new Token("TOKEN_SPACE", sb.toString());
                    return result;
                case '\n':
                    sb.append(symbol);
                    result = nextToken;
                    nextToken = new Token("TOKEN_NEWLINE", sb.toString());
                    return result;
                case '\t':
                    sb.append(symbol);
                    result = nextToken;
                    nextToken = new Token("TOKEN_TABULATION", sb.toString());
                    return result;
                case ';':
                    sb.append(symbol);
                    result = nextToken;
                    nextToken = new Token("TOKEN_SEMICOLON", sb.toString());
                    return result;
                default:
                    sb.append(symbol);
                    result = nextToken;
                    nextToken = new Token("TOKEN_NON-DIVIDER", sb.toString());
                    return result;
            }
        }
        result = nextToken;
        nextToken = new Token("TOKEN_EMPTY", sb.toString());
        return result;
    }

    /**
     * Check if lexer has another token to read
     * @return true if has next token, else return false
     */

    public boolean hasNextToken() {
        return "TOKEN_EMPTY".equals(nextToken.getName());
    }

    /**
     * Automatically calls when closing this object
     * @throws IOException if close is not supported
     * @see AutoCloseable
     */

    public void close() throws IOException {
        fileReader.close();
    }
}
