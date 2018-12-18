package it.sevenbits.formater.javaFormater.formater.command;

import it.sevenbits.formater.javaFormater.formater.container.IFormaterBufferContainer;
import it.sevenbits.formater.javaFormater.formater.StateMachineJavaFormater;

import java.io.IOException;

public class FormaterWriteCommand implements IFormaterCommand {

    private static IFormaterBufferContainer container = StateMachineJavaFormater.getBuffer();

    public void execute() throws IOException {
            container.write();
    }
}
