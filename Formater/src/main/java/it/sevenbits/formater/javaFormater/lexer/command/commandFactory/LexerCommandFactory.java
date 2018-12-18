package it.sevenbits.formater.javaFormater.lexer.command.commandFactory;

import it.sevenbits.formater.javaFormater.lexer.command.*;
import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;

import java.util.HashMap;

public class LexerCommandFactory implements ILexerCommandFactory {

    private HashMap<Pair<Integer, State>, ILexerCommand> hashMap;
    private final ILexerCommand ignoreCommand;
    private final ILexerCommand copyCommand;
    private final ILexerCommand addAndRenameCommand;
    private final ILexerCommand preserveExtraCommand;
    private final ILexerCommand preserveAndDiscardCommand;
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

    public LexerCommandFactory() {
        copyCommand = new LexerAddCommand();
        ignoreCommand = new LexerIgnoreCommand();
        addAndRenameCommand = new LexerAddAndRenameCommand();
        preserveExtraCommand = new LexerPreserveExtraCommand();
        preserveAndDiscardCommand = new LexerPreserveAndDiscardCommand();
        this.hashMap = new HashMap<>();

        hashMap.put(new Pair<>(space, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(letterLowercase, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(letterUppercase, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(otherSymbols, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(slash, new State("START")), copyCommand);
        hashMap.put(new Pair<>(star, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(endline, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(singleQuotation, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(doubleQuotation, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(digit, new State("START")), addAndRenameCommand);

        hashMap.put(new Pair<>(space, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(letterLowercase, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(letterUppercase, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(otherSymbols, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(slash, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(star, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(endline, new State("ONE_LINE_COMMENTARY")), preserveExtraCommand);
        hashMap.put(new Pair<>(singleQuotation, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(doubleQuotation, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(digit, new State("ONE_LINE_COMMENTARY")), copyCommand);

        hashMap.put(new Pair<>(space, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(letterLowercase, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(letterUppercase, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(otherSymbols, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(slash, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(star, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(endline, new State("STRING_LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(singleQuotation, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(doubleQuotation, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(digit, new State("STRING_LITERAL")), copyCommand);

        hashMap.put(new Pair<>(space, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(letterLowercase, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(letterUppercase, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(otherSymbols, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(slash, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(star, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(endline, new State("CHAR_LITERAL_START")),  preserveAndDiscardCommand);
        hashMap.put(new Pair<>(singleQuotation, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(doubleQuotation, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(digit, new State("CHAR_LITERAL_START")), copyCommand);

        hashMap.put(new Pair<>(space, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(letterLowercase, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(letterUppercase, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(otherSymbols, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(slash, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(star, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(endline, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(singleQuotation, new State("CHAR_LITERAL_END")), copyCommand);
        hashMap.put(new Pair<>(doubleQuotation, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(digit, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);

        hashMap.put(new Pair<>(space, new State("SPACE")), copyCommand);
        hashMap.put(new Pair<>(letterLowercase, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(letterUppercase, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(otherSymbols, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(slash, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(star, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(endline, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(singleQuotation, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(doubleQuotation, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(digit, new State("SPACE")), preserveExtraCommand);

        hashMap.put(new Pair<>(space, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(letterLowercase, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(letterUppercase, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(otherSymbols, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(slash, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(star, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(endline, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(singleQuotation, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(doubleQuotation, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(digit, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);

        hashMap.put(new Pair<>(space, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(letterLowercase, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(letterUppercase, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(otherSymbols, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(slash, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(star, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(endline, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(singleQuotation, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(doubleQuotation, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(digit, new State("NUMBER")), copyCommand);

        hashMap.put(new Pair<>(space, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(letterLowercase, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(letterUppercase, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(otherSymbols, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(slash, new State("COMMENT_SUSPICION")), copyCommand);
        hashMap.put(new Pair<>(star, new State("COMMENT_SUSPICION")), copyCommand);
        hashMap.put(new Pair<>(endline, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(singleQuotation, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(doubleQuotation, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(digit, new State("OPERATION_SYMBOLCOMMENT_SUSPICION")),  preserveExtraCommand);

        hashMap.put(new Pair<>(space, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(letterLowercase, new State("LITERAL")), copyCommand);
        hashMap.put(new Pair<>(letterUppercase, new State("LITERAL")), copyCommand);
        hashMap.put(new Pair<>(otherSymbols, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(slash, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(star, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(endline, new State("LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(singleQuotation, new State("LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(doubleQuotation, new State("LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(digit, new State("LITERAL")),  copyCommand);
    }

    public ILexerCommand getCommand(final int symbol, final State state) {
        return hashMap.getOrDefault(new Pair<>(symbol , state), ignoreCommand);
    }
}
