package it.sevenbits.formater.javaFormater.formater;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.io.output.IWriter;
import it.sevenbits.formater.io.output.bufferedWriter.BufferedWriter;
import it.sevenbits.formater.javaFormater.formater.command.commandFactory.FormaterCommandFactory;
import it.sevenbits.formater.javaFormater.formater.container.FormaterBufferContainer;
import it.sevenbits.formater.javaFormater.formater.container.IFormaterBufferContainer;
import it.sevenbits.formater.javaFormater.stateMachine.State;
import it.sevenbits.formater.javaFormater.stateMachine.formater.FormaterStateTransition;
import it.sevenbits.formater.javaFormater.lexer.factory.ILexerFactory;
import it.sevenbits.formater.javaFormater.lexer.ILexer;
import it.sevenbits.formater.javaFormater.lexer.token.IToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class StateMachineJavaFormater implements IFormater {

    private static Logger logger = LoggerFactory.getLogger(StateMachineJavaFormater.class);
    private FormaterCommandFactory formaterCommandFactory;
    private final FormaterStateTransition formaterStateTransition;
    private ILexerFactory lexerFactory;
    private static IFormaterBufferContainer container;

    public StateMachineJavaFormater(final ILexerFactory lexerFactory) {
        this.lexerFactory = lexerFactory;
        container = new FormaterBufferContainer();
        this.formaterStateTransition = new FormaterStateTransition();
        this.formaterCommandFactory = new FormaterCommandFactory();
    }

    public void format(final IReader reader, final IWriter writer) throws IOException {
        container.setDestination(new BufferedWriter(writer));
        ILexer lexer = this.lexerFactory.createLexer(reader);
        IToken lastToken;
        State currentState = formaterStateTransition.getStartState();
        while (lexer.hasNextToken()) {
            IToken token = lexer.getNextToken();
            container.setNextString(token.getLexeme());
            formaterCommandFactory.getCommand(token, currentState).execute();
            currentState = formaterStateTransition.nextState(currentState, token);
            lastToken = token;
        }
    }

    public static IFormaterBufferContainer getBuffer() {
        return container;
    }
}
