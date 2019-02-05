package it.sevenbits.formater.javaFormater.formater.command;

import it.sevenbits.formater.javaFormater.formater.StateMachineJavaFormater;
import it.sevenbits.formater.javaFormater.formater.container.IFormaterBufferContainer;

import java.io.IOException;

public class FormaterNewLineDecAndMakeTabulationAndWriteCommand implements IFormaterCommand {
    private static IFormaterBufferContainer container = StateMachineJavaFormater.getBuffer();

    public void execute() throws IOException {
        container.writeLine();
        container.setTabulationCount(container.getTabulationCount() - 1);
        container.writeTabulation();
        container.write();
    }
}
