package it.sevenbits.formater.javaFormater.formater.command.commandFactory;

import it.sevenbits.formater.javaFormater.formater.command.*;
import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;
import it.sevenbits.formater.javaFormater.lexer.token.IToken;

import java.util.HashMap;

public class FormaterCommandFactory implements IFormaterCommandFactory {

    private HashMap<Pair<State,String>, IFormaterCommand> hashMap;
    private final IFormaterCommand writeCommand;

    public FormaterCommandFactory() {
        final String spaceToken = "TOKEN_SPACE";
        final String stringLiteralToken = "TOKEN_STRING_LITERAL";
        final String literalToken = "TOKEN_LITERAL";
        final String semicolonToken = "TOKEN_SEMICOLON";
        final String charLiteralToken = "TOKEN_CHAR_LITERAL";
        final String starToken = "TOKEN_STAR";
        final String otherSymbolsToken = "TOKEN_OTHER";
        final String emptyToken = "TOKEN_EMPTY";
        final String leftBraceToken = "TOKEN_LEFTBRACE";
        final String rightBraceToken = "TOKEN_RIGHTBRACE";

        State startState = new State("START_OF_LINE");
        State lexemState = new State("LEXEM");
        State endLineSuspicionState = new State("END_OF_LINE_SUSPICION");
        State lineCommentaryState = new State("LINE_COMMENTARY");

        final IFormaterCommand ignoreCommand;
        final IFormaterCommand newLineAndWriteCommand;
        final IFormaterCommand tabulationCommand;
        final IFormaterCommand tabulationAndWriteCommand;
        final IFormaterCommand newLineTabulationAndWriteCommand;
        final IFormaterCommand writeAndIncTabulationCommand;
        final IFormaterCommand writeAndDecTabulationCommand;
        final IFormaterCommand newLineDecAndMakeTabulationAndWriteCommand;

        writeCommand = new FormaterWriteCommand();
        ignoreCommand = new FormaterIgnoreCommand();
        newLineAndWriteCommand = new FormaterAddNewLineAndWriteCommand();
        tabulationCommand = new FormaterTabulationCommand();
        newLineTabulationAndWriteCommand = new FormaterNewLineTabulationAndWriteCommand();
        tabulationAndWriteCommand = new FormaterTabulationAndWriteCommand();
        writeAndIncTabulationCommand = new FormaterWriteAndIncTabulationCountCommand();
        writeAndDecTabulationCommand = new FormaterWriteAndDecTabulationCountCommand();
        newLineDecAndMakeTabulationAndWriteCommand =
                new FormaterNewLineDecAndMakeTabulationAndWriteCommand();

        this.hashMap = new HashMap<>();

        hashMap.put(new Pair<>(startState, spaceToken), ignoreCommand);
        hashMap.put(new Pair<>(startState, emptyToken), ignoreCommand);
        hashMap.put(new Pair<>(startState, stringLiteralToken), tabulationAndWriteCommand);
        hashMap.put(new Pair<>(startState, literalToken), tabulationAndWriteCommand);
        hashMap.put(new Pair<>(startState, charLiteralToken), tabulationAndWriteCommand);
        hashMap.put(new Pair<>(startState, leftBraceToken), writeAndIncTabulationCommand);
        hashMap.put(new Pair<>(startState, rightBraceToken), writeAndDecTabulationCommand);

        hashMap.put(new Pair<>(lexemState, emptyToken), ignoreCommand);
        hashMap.put(new Pair<>(lexemState, leftBraceToken), writeAndIncTabulationCommand);
        hashMap.put(new Pair<>(lexemState, rightBraceToken), writeAndDecTabulationCommand);

        hashMap.put(new Pair<>(endLineSuspicionState, emptyToken), ignoreCommand);
        hashMap.put(new Pair<>(endLineSuspicionState, stringLiteralToken), newLineTabulationAndWriteCommand);
        hashMap.put(new Pair<>(endLineSuspicionState, literalToken), newLineTabulationAndWriteCommand);
        hashMap.put(new Pair<>(endLineSuspicionState, charLiteralToken), newLineTabulationAndWriteCommand);
        hashMap.put(new Pair<>(endLineSuspicionState, semicolonToken), newLineTabulationAndWriteCommand);
        hashMap.put(new Pair<>(endLineSuspicionState, otherSymbolsToken), newLineTabulationAndWriteCommand);
        hashMap.put(new Pair<>(endLineSuspicionState, starToken), newLineTabulationAndWriteCommand);
        hashMap.put(new Pair<>(endLineSuspicionState, rightBraceToken),
                newLineDecAndMakeTabulationAndWriteCommand);
    }

    public IFormaterCommand getCommand(final IToken token, final State state) {
        return hashMap.getOrDefault(new Pair<>(state, token.getName()), writeCommand);
    }
}
