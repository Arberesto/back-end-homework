package it.sevenbits.formater.javaFormater.formater.command;

import it.sevenbits.formater.javaFormater.formater.StateMachineJavaFormater;
import it.sevenbits.formater.javaFormater.formater.container.IFormaterBufferContainer;

import java.io.IOException;

public class FormaterNewLineTabulationAndWriteCommand implements IFormaterCommand {

    private static IFormaterBufferContainer container = StateMachineJavaFormater.getBuffer();

    public void execute() throws IOException {
            container.writeLine();
            container.writeTabulation();
            container.write();
    }
}
