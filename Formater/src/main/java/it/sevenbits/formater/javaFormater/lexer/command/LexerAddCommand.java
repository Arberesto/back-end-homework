package it.sevenbits.formater.javaFormater.lexer.command;

import it.sevenbits.formater.javaFormater.lexer.StateMachineLexer;
import it.sevenbits.formater.javaFormater.lexer.container.ILexerBufferContainer;

public class LexerAddCommand implements ILexerCommand {

    private ILexerBufferContainer container = StateMachineLexer.getBuffer();

    public void execute() {
        container.addSymbol();
    }
}
