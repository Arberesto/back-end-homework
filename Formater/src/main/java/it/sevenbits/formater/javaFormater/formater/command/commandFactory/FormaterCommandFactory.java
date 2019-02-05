package it.sevenbits.formater.javaFormater.formater.command.commandFactory;

import it.sevenbits.formater.javaFormater.formater.command.*;
import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;
import it.sevenbits.formater.javaFormater.lexer.token.IToken;

import java.util.HashMap;

public class FormaterCommandFactory implements IFormaterCommandFactory {

    private HashMap<Pair<String, State>, IFormaterCommand> hashMap;
    private final IFormaterCommand writeCommand;

    public FormaterCommandFactory() {
        final String spaceToken = "TOKEN_SPACE";
        final String lineCommentaryToken = "TOKEN_LINE_COMMENTARY";
        final String stringLiteralToken = "TOKEN_STRING_LITERAL";
        final String literalToken = "TOKEN_LITERAL";
        final String numberToken = "TOKEN_NUMBER";
        final String endlineToken = "TOKEN_ENDLINE";
        final String semicolonToken = "TOKEN_SEMICOLON";
        final String charLiteralToken = "TOKEN_CHAR_LITERAL";
        final String otherSymbolsToken = "TOKEN_OTHER";
        final String starToken = "TOKEN_STAR";
        final String emptyToken = "TOKEN_EMPTY";

        final State startState = new State("START");
        final State endState = new State("END");
        final State errorState = new State("ERROR");
        final State lineCommentaryState = new State("ONE_LINE_COMMENTARY");
        final State stringLiteralState = new State("STRING_LITERAL");
        final State charLiteralStartState = new State("CHAR_LITERAL_START");
        final State charLiteralEndState = new State("CHAR_LITERAL_END");
        final State spaceState = new State("SPACE");
        final State literalState = new State("LITERAL");
        final State operationSymbolState = new State("OPERATION_SYMBOL");
        final State numberState = new State("NUMBER");
        final State commentSuspicionState = new State("COMMENT_SUSPICION");

        final IFormaterCommand ignoreCommand;
        final IFormaterCommand newLineAndWriteCommand;
        final IFormaterCommand tabulationCommand;
        final IFormaterCommand writeAndIncTabulationCommand;
        final IFormaterCommand writeAndDecTabulationCommand;
        writeCommand = new FormaterWriteCommand();
        ignoreCommand = new FormaterIgnoreCommand();
        newLineAndWriteCommand = new FormaterAddNewLineAndWriteCommand();
        tabulationCommand = new FormaterTabulationCommand();
        writeAndIncTabulationCommand = new FormaterWriteAndIncTabulationCountCommand();
        writeAndDecTabulationCommand = new FormaterWriteAndDecTabulationCountCommand();
        this.hashMap = new HashMap<>();
    }

    public IFormaterCommand getCommand(final IToken token, final State state) {
        return hashMap.getOrDefault(new Pair<>(token.getName() , state), writeCommand);
    }
}
