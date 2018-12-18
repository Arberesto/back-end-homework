package it.sevenbits.formater.javaFormater.lexer.command.commandFactory;

import it.sevenbits.formater.javaFormater.lexer.command.ILexerCommand;
import it.sevenbits.formater.javaFormater.stateMachine.State;

public interface ILexerCommandFactory {

    ILexerCommand getCommand(char symbol, State state);
}
