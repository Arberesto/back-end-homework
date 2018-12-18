package it.sevenbits.formater.javaFormater.lexer;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.javaFormater.lexer.token.IToken;
import it.sevenbits.formater.javaFormater.lexer.token.Token;

import java.io.IOException;

/**
 * Class for reading tokens from stream
 */

public class Lexer implements ILexer {

    private IReader reader;
    private IToken nextToken;

    /**
     * Assign its own reader with param1 object
     * @throws IOException if error occurred in reader
     * @param reader object that bond with Lexer Reader
     */

    public Lexer(final IReader reader) throws IOException {
        this.reader = reader;
        try {
            this.nextToken = getRealNextToken();
        } catch (IOException e) {
            nextToken = new Token();
            throw new IOException("lexer init IO error");
        }
    }

    /**
     * Return next token readed from Reader
     *
     * @return IToken object
     */

    public IToken getNextToken() {
        IToken result;
        result = nextToken;
        try {
            nextToken = getRealNextToken();
        } catch (IOException e) {
            nextToken = new Token();
        }
        return result;
    }

    /**
     * Read next token from Reader
     *
     * @return IToken object
     * @throws IOException if error occurred in Reader
     */

    private IToken getRealNextToken() throws IOException {
        StringBuilder sb = new StringBuilder();
        if (reader.hasNext()) {
            char symbol = (char) reader.read();
            switch (symbol) {
                case '{':
                    sb.append(symbol);
                    return new Token("TOKEN_LEFTBRACE", sb.toString());
                case '}':
                    sb.append(symbol);
                    return new Token("TOKEN_RIGHTBRACE", sb.toString());
                case ' ':
                    sb.append(symbol);
                    return new Token("TOKEN_SPACE", sb.toString());
                case '\n':
                    sb.append(symbol);
                    return new Token("TOKEN_NEWLINE", sb.toString());
                case '\t':
                    sb.append(symbol);
                    return new Token("TOKEN_TABULATION", sb.toString());
                case ';':
                    sb.append(symbol);
                    return new Token("TOKEN_SEMICOLON", sb.toString());
                default:
                    sb.append(symbol);
                    return new Token("TOKEN_NON-DIVIDER", sb.toString());
            }
        }
        throw new IOException("End of stream reached");
    }

    /**
     * Check if lexer has another token to read
     *
     * @return true if has next token, else return false
     */

    public boolean hasNextToken() {
        return !("TOKEN_EMPTY".equals(nextToken.getName()));
    }

    /**
     * Automatically calls when closing this object
     *
     * @throws IOException if close is not supported
     * @see AutoCloseable
     */

    public void close() throws IOException {
        reader.close();
    }
}
