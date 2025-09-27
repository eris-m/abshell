package code.eris.abshell.ast;

import code.eris.abshell.ProcessCreator;
import code.eris.abshell.Shell;
import code.eris.abshell.error.ExecutionException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CommandExpression implements ExecutableExpression {
    public CommandExpression() {
        this("", new ArrayList<>());
    }
    
    public CommandExpression(String commandName, List<ValueExpression> args) {
        this.commandName = commandName;
        this.args = args;
    }

    @Override
    public int execute(Shell shell) {
        return isCommandlet(shell)
                ? executeCommandlet(shell)
                : executeOrdinary(shell);
    }
    
    public String getCommandName() {
        return commandName;
    }

    public List<ValueExpression> getArgs() {
        return args;
    }

    private boolean isCommandlet(Shell shell) {
        return shell.getCommandlets().containsKey(commandName);
    }

    private int executeCommandlet(Shell shell) {
        List<Object> args = evaluateArguments(shell).toList();

        try {
            return shell.getCommandlets().get(commandName).execute(shell, args);
        } catch (ExecutionException e) {
            System.err.println(e.getLocalizedMessage());
            return -1;
        }
    }

    private int executeOrdinary(Shell shell) {
        ProcessCreator pc = shell.getProcessBuilder();
        pc.withCommand(commandName);

        List<String> args = evaluateArguments(shell).map(Object::toString).toList();
        pc.withArgs(args);

        try {
            Process process = pc.start();
            return process.waitFor();
        } catch (IOException | InterruptedException ex) {
            System.err.println(ex.getLocalizedMessage());
            return -1;
        }
    }

    private Stream<Object> evaluateArguments(Shell shell) {
        return args
                .stream()
                .map(a -> a.evaluate(shell));
    }

    private final List<ValueExpression> args;

    private final String commandName;
}
