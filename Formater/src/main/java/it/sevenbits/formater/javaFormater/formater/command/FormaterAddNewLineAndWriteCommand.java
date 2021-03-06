package it.sevenbits.formater.javaFormater.formater.command;

import it.sevenbits.formater.javaFormater.formater.StateMachineJavaFormater;
import it.sevenbits.formater.javaFormater.formater.container.IFormaterBufferContainer;

import java.io.IOException;

public class FormaterAddNewLineAndWriteCommand implements IFormaterCommand {
    private static IFormaterBufferContainer container = StateMachineJavaFormater.getBuffer();

    public void execute() throws IOException {
        container.writeLine();
        container.write();
    }
}
