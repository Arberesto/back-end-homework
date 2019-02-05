package it.sevenbits.formater.javaFormater.lexer.command.commandFactory;

import it.sevenbits.formater.javaFormater.lexer.command.ILexerCommand;
import it.sevenbits.formater.javaFormater.lexer.command.LexerAddCommand;
import it.sevenbits.formater.javaFormater.lexer.command.LexerIgnoreCommand;
import it.sevenbits.formater.javaFormater.lexer.command.LexerAddAndRenameCommand;
import it.sevenbits.formater.javaFormater.lexer.command.LexerPreserveAndDiscardCommand;
import it.sevenbits.formater.javaFormater.lexer.command.LexerPreserveExtraCommand;
import it.sevenbits.formater.javaFormater.lexer.token.symbolGroups.SymbolGroups;
import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;
import java.util.HashMap;

/**
 *
 */

public class LexerCommandFactory implements ILexerCommandFactory {

    private HashMap<Pair<Integer, State>, ILexerCommand> hashMap;
    private final ILexerCommand ignoreCommand;

    /**
     * Constructor fills HashMap
     */

    public LexerCommandFactory() {
        final ILexerCommand copyCommand;
        final ILexerCommand addAndRenameCommand;
        final ILexerCommand preserveExtraCommand;
        final ILexerCommand preserveAndDiscardCommand;
        copyCommand = new LexerAddCommand();
        ignoreCommand = new LexerIgnoreCommand();
        addAndRenameCommand = new LexerAddAndRenameCommand();
        preserveExtraCommand = new LexerPreserveExtraCommand();
        preserveAndDiscardCommand = new LexerPreserveAndDiscardCommand();
        this.hashMap = new HashMap<>();

        hashMap.put(new Pair<>(SymbolGroups.SPACE, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.SLASH, new State("START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.STAR, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOT, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.SEMICOLON, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.ENDLINE, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.DIGIT, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.LEFT_BRACE, new State("START")), addAndRenameCommand);
        hashMap.put(new Pair<>(SymbolGroups.RIGHT_BRACE, new State("START")), addAndRenameCommand);

        hashMap.put(new Pair<>(SymbolGroups.SPACE, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.SLASH, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.STAR, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOT, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.SEMICOLON, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.ENDLINE, new State("ONE_LINE_COMMENTARY")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.DIGIT, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LEFT_BRACE, new State("ONE_LINE_COMMENTARY")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.RIGHT_BRACE, new State("ONE_LINE_COMMENTARY")), copyCommand);

        hashMap.put(new Pair<>(SymbolGroups.SPACE, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.SLASH, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.STAR, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOT, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.SEMICOLON, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.ENDLINE, new State("STRING_LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.DIGIT, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LEFT_BRACE, new State("STRING_LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.RIGHT_BRACE, new State("STRING_LITERAL")), copyCommand);

        hashMap.put(new Pair<>(SymbolGroups.SPACE, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.SLASH, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.STAR, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOT, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.SEMICOLON, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.ENDLINE, new State("CHAR_LITERAL_START")),  preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.DIGIT, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LEFT_BRACE, new State("CHAR_LITERAL_START")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.RIGHT_BRACE, new State("CHAR_LITERAL_START")), copyCommand);


        hashMap.put(new Pair<>(SymbolGroups.SPACE, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.SLASH, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.STAR, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOT, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.SEMICOLON, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.ENDLINE, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, new State("CHAR_LITERAL_END")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.DIGIT, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.LEFT_BRACE, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.RIGHT_BRACE, new State("CHAR_LITERAL_END")),
                preserveAndDiscardCommand);

        hashMap.put(new Pair<>(SymbolGroups.SPACE, new State("SPACE")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.SLASH, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.STAR, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOT, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.SEMICOLON, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.ENDLINE, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.DIGIT, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.LEFT_BRACE, new State("SPACE")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.RIGHT_BRACE, new State("SPACE")), preserveExtraCommand);

        hashMap.put(new Pair<>(SymbolGroups.SPACE, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.SLASH, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.STAR, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOT, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.SEMICOLON, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.ENDLINE, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.DIGIT, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.LEFT_BRACE, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.RIGHT_BRACE, new State("OPERATION_SYMBOL")), preserveAndDiscardCommand);


        hashMap.put(new Pair<>(SymbolGroups.SPACE, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.SLASH, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.STAR, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOT, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.SEMICOLON, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.ENDLINE, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.DIGIT, new State("NUMBER")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LEFT_BRACE, new State("NUMBER")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.RIGHT_BRACE, new State("NUMBER")), preserveExtraCommand);

        hashMap.put(new Pair<>(SymbolGroups.SPACE, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.SLASH, new State("COMMENT_SUSPICION")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.STAR, new State("COMMENT_SUSPICION")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOT, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.SEMICOLON, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.ENDLINE, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, new State("COMMENT_SUSPICION")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.DIGIT, new State("COMMENT_SUSPICION")),  preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.LEFT_BRACE, new State("COMMENT_SUSPICION")),  preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.RIGHT_BRACE, new State("COMMENT_SUSPICION")),  preserveExtraCommand);

        hashMap.put(new Pair<>(SymbolGroups.SPACE, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_LOWERCASE, new State("LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LETTER_UPPERCASE, new State("LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.OTHER_SYMBOLS, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.SLASH, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.STAR, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOT, new State("LITERAL")), copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.SEMICOLON, new State("LITERAL")), preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.ENDLINE, new State("LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.SINGLE_QUOTATION, new State("LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.DOUBLE_QUOTATION, new State("LITERAL")), preserveAndDiscardCommand);
        hashMap.put(new Pair<>(SymbolGroups.DIGIT, new State("LITERAL")),  copyCommand);
        hashMap.put(new Pair<>(SymbolGroups.LEFT_BRACE, new State("LITERAL")),  preserveExtraCommand);
        hashMap.put(new Pair<>(SymbolGroups.RIGHT_BRACE, new State("LITERAL")),  preserveExtraCommand);
    }

    /**
     * Get command based on current symbol and state
     * @param symbol current symbol
     * @param state current state
     * @return new Command or ignoreCommand if can't find in map
     */

    public ILexerCommand getCommand(final int symbol, final State state) {
        return hashMap.getOrDefault(new Pair<>(symbol , state), ignoreCommand);
    }
}
