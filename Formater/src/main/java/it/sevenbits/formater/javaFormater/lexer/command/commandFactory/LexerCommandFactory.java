package it.sevenbits.formater.javaFormater.lexer.command.commandFactory;

import it.sevenbits.formater.javaFormater.lexer.command.*;
import it.sevenbits.formater.javaFormater.lexer.token.symbolGroups.SymbolGroups;
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

    public LexerCommandFactory() {
        copyCommand = new LexerAddCommand();
        ignoreCommand = new LexerIgnoreCommand();
        addAndRenameCommand = new LexerAddAndRenameCommand();
        preserveExtraCommand = new LexerPreserveExtraCommand();
        preserveAndDiscardCommand = new LexerPreserveAndDiscardCommand();
        this.hashMap = new HashMap<>();

        hashMap.put(new Pair<>(SymbolGroups.space, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterLowercase, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterUppercase, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.otherSymbols, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.slash, new State("START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.star, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.endline, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.singleQuotation, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.doubleQuotation, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.digit, new State("START")), addAndRenameCommand);

        hashMap.put(new Pair<>(SymbolGroups.space, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterLowercase, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterUppercase, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.otherSymbols, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.slash, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.star, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.endline, new State("ONE_LINE_COMMENTARY")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.singleQuotation, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.doubleQuotation, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.digit, new State("ONE_LINE_COMMENTARY")), copyCommand);

        hashMap.put(new Pair<>(SymbolGroups.space, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterLowercase, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterUppercase, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.otherSymbols, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.slash, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.star, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.endline, new State("STRING_LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.singleQuotation, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.doubleQuotation, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.digit, new State("STRING_LITERAL")), copyCommand);

        hashMap.put(new Pair<>(SymbolGroups.space, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterLowercase, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterUppercase, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.otherSymbols, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.slash, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.star, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.endline, new State("CHAR_LITERAL_START")),  preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.singleQuotation, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.doubleQuotation, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.digit, new State("CHAR_LITERAL_START")), copyCommand);

        hashMap.put(new Pair<>(SymbolGroups.space, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterLowercase, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterUppercase, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.otherSymbols, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.slash, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.star, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.endline, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.singleQuotation, new State("CHAR_LITERAL_END")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.doubleQuotation, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.digit, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);

        hashMap.put(new Pair<>(SymbolGroups.space, new State("SPACE")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterLowercase, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterUppercase, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.otherSymbols, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.slash, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.star, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.endline, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.singleQuotation, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.doubleQuotation, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.digit, new State("SPACE")), preserveExtraCommand);

        hashMap.put(new Pair<>(SymbolGroups.space, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterLowercase, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterUppercase, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.otherSymbols, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.slash, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.star, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.endline, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.singleQuotation, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.doubleQuotation, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.digit, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);

        hashMap.put(new Pair<>(SymbolGroups.space, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterLowercase, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterUppercase, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.otherSymbols, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.slash, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.star, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.endline, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.singleQuotation, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.doubleQuotation, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.digit, new State("NUMBER")), copyCommand);

        hashMap.put(new Pair<>(SymbolGroups.space, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterLowercase, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterUppercase, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.otherSymbols, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.slash, new State("COMMENT_SUSPICION")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.star, new State("COMMENT_SUSPICION")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.endline, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.singleQuotation, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.doubleQuotation, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.digit, new State("COMMENT_SUSPICION")),  preserveExtraCommand);

        hashMap.put(new Pair<>(SymbolGroups.space, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterLowercase, new State("LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.letterUppercase, new State("LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.otherSymbols, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.slash, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.star, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.endline, new State("LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.singleQuotation, new State("LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.doubleQuotation, new State("LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.digit, new State("LITERAL")),  copyCommand);
    }

    public ILexerCommand getCommand(final int symbol, final State state) {
        return hashMap.getOrDefault(new Pair<>(symbol , state), ignoreCommand);
    }
}
