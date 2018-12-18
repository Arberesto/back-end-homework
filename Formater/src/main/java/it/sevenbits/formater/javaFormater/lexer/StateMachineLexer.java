package it.sevenbits.formater.javaFormater.lexer;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.javaFormater.lexer.command.ILexerCommand;
import it.sevenbits.formater.javaFormater.lexer.command.commandFactory.LexerCommandFactory;
import it.sevenbits.formater.javaFormater.lexer.container.ILexerBufferContainer;
import it.sevenbits.formater.javaFormater.lexer.container.LexerBufferContainer;
import it.sevenbits.formater.javaFormater.stateMachine.State;
import it.sevenbits.formater.javaFormater.stateMachine.lexer.LexerStateTransition;
import it.sevenbits.formater.javaFormater.lexer.token.IToken;
import it.sevenbits.formater.javaFormater.lexer.token.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class StateMachineLexer implements ILexer {
    private static Logger logger = LoggerFactory.getLogger(StateMachineLexer.class);
    private IReader reader;
    private IToken nextToken;
    private LexerStateTransition lexerStateTransition;
    private LexerCommandFactory lexerCommandFactory;
    private static ILexerBufferContainer container;

    /**
     * Assign its own reader with param1 object
     *
     * @param reader object that bond with Lexer Reader
     * @throws IOException if error occurred in reader
     */

    public StateMachineLexer(final IReader reader) throws IOException {
        this.reader = reader;
        lexerStateTransition = new LexerStateTransition();
        container = new LexerBufferContainer();
        lexerCommandFactory = new LexerCommandFactory();
        try {
            this.nextToken = getRealNextToken();
        } catch (IOException e) {
            nextToken = new Token();
            throw new IOException("lexer init IO error");
        }
    }

    public static ILexerBufferContainer getBuffer() {
        return container;
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
        container.clear();
        container.setNextName("TOKEN_NEXT");
        State currentState = lexerStateTransition.getStartState();
        State finishState = lexerStateTransition.getEndState();
        State errorState = lexerStateTransition.getErrorState();
        while (reader.hasNext() && currentState != finishState && currentState != errorState ) {
            char symbol = (char) reader.predictNext();
            logger.info("Current symbol: " + symbol);
            logger.info("Current symbolID: " + Integer.toString(getSymbolID(symbol)));
            container.setNextSymbol(symbol);
            ILexerCommand command = lexerCommandFactory.getCommand(getSymbolID(symbol), currentState);
            logger.info("Current command to execute: " + command.getClass().getName());
            command.execute();
            currentState = lexerStateTransition.nextState(currentState, getSymbolID(symbol));
            if (!errorState.equals(currentState)) {
                reader.read();
            }
            logger.info("Current state: " + currentState.toString());
        }
        return container.getToken();
    }

    public int getSymbolID(final char symbol) {
        if (Character.isDigit(symbol)) {
            return LexerCommandFactory.digit;
        }

        if (Character.isLetter(symbol)) {
            if (Character.isLowerCase(symbol)) {
                return LexerCommandFactory.letterLowercase;
            } else {
                return LexerCommandFactory.letterUppercase;
            }
        }

        if (symbol == '"') {
            return LexerCommandFactory.doubleQuotation;
        }

        if (symbol == "'".charAt(0)) {
            return LexerCommandFactory.singleQuotation;
        }

        if (symbol == '\n') {
            return LexerCommandFactory.endline;
        }

        if (symbol == '/') {
            return LexerCommandFactory.slash;
        }

        if (symbol == '*') {
            return LexerCommandFactory.star;
        }

        if (symbol == ' ') {
            return LexerCommandFactory.space;
        }

        return LexerCommandFactory.otherSymbols;
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
