package it.sevenbits.formater.javaFormater.formater.command.commandFactory;

import it.sevenbits.formater.javaFormater.formater.command.IFormaterCommand;
import it.sevenbits.formater.javaFormater.stateMachine.State;
import it.sevenbits.formater.javaFormater.lexer.token.IToken;

public interface IFormaterCommandFactory {

    IFormaterCommand getCommand(IToken token, State state);
}
