package it.sevenbits.formater.javaFormater.lexer.command.commandFactory;

import it.sevenbits.formater.javaFormater.lexer.command.ILexerCommand;
import it.sevenbits.formater.javaFormater.lexer.command.LexerCopyCommand;
import it.sevenbits.formater.javaFormater.lexer.command.LexerIgnoreCommand;
import it.sevenbits.formater.javaFormater.lexer.command.LexerAddAndRenameCommand;
import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;

import java.util.HashMap;

public class LexerCommandFactory implements ILexerCommandFactory {

    private HashMap<Pair<Character, State>, ILexerCommand> hashMap;
    private final ILexerCommand ignoreCommand;
    private final ILexerCommand copyCommand;
    private final ILexerCommand addAndRenameCommand;

    public LexerCommandFactory() {
        copyCommand = new LexerCopyCommand();
        ignoreCommand = new LexerIgnoreCommand();
        addAndRenameCommand = new LexerAddAndRenameCommand();
        this.hashMap = new HashMap<>();
        hashMap.put(new Pair<>('{',new State("LITERAL")), addAndRenameCommand);
    }

    public ILexerCommand getCommand(final char symbol, final State state) {
        return hashMap.getOrDefault(new Pair<>(symbol , state), copyCommand);
    }
}
