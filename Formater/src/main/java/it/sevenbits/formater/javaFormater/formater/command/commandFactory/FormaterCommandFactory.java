package it.sevenbits.formater.javaFormater.formater.command.commandFactory;

import it.sevenbits.formater.javaFormater.formater.command.FormaterIgnoreCommand;
import it.sevenbits.formater.javaFormater.formater.command.FormaterWriteCommand;
import it.sevenbits.formater.javaFormater.formater.command.IFormaterCommand;
import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;
import it.sevenbits.formater.javaFormater.lexer.token.IToken;

import java.util.HashMap;

public class FormaterCommandFactory implements IFormaterCommandFactory {

    private HashMap<Pair<String, State>, IFormaterCommand> hashMap;
    private final IFormaterCommand ignoreCommand;
    private final IFormaterCommand writeCommand;

    public FormaterCommandFactory() {
        writeCommand = new FormaterWriteCommand();
        ignoreCommand = new FormaterIgnoreCommand();
        this.hashMap = new HashMap<>();
    }

    public IFormaterCommand getCommand(final IToken token, final State state) {
        return hashMap.getOrDefault(new Pair<>(token.getName() , state), writeCommand);
    }
}
