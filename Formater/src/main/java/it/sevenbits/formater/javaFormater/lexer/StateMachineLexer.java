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

import java.io.IOException;

public class StateMachineLexer implements ILexer {
    private IReader reader;
    private IToken nextToken;
    private LexerStateTransition lexerStateTransition;
    private LexerCommandFactory lexerCommandFactory;
    private static ILexerBufferContainer container;
    /**
     * Assign its own reader with param1 object
     * @throws IOException if error occurred in reader
     * @param reader object that bond with Lexer Reader
     */

    public StateMachineLexer (final IReader reader) throws IOException {
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
        container.setNextName("TOKEN_ONESHOT");
        State currentState = lexerStateTransition.getStartState();
        State finishState = lexerStateTransition.getEndState();
        while ((reader.hasNext()) && (currentState != finishState)) {
            char symbol = (char) reader.read();
            container.setNextSymbol(symbol);
            ILexerCommand command = lexerCommandFactory.getCommand(symbol, currentState);
            command.execute();
            currentState = lexerStateTransition.nextState(currentState, symbol);
        }
        return container.getToken();
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
