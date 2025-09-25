package code.eris.abshell.ast;

import code.eris.abshell.ProcessCreator;
import code.eris.abshell.Shell;
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
        ProcessCreator pc = shell.getProcessBuilder();
        pc.withCommand(commandName);

        Stream<String> args = evaluateArguments(shell);
        pc.withArgs(args.toList());

        try {
            Process process = pc.start();
            return process.waitFor();
        } catch (IOException | InterruptedException ex) {
            System.err.println(ex.getLocalizedMessage());
            return -1;
        }
    }
    
    public String getCommandName() {
        return commandName;
    }

    public List<ValueExpression> getArgs() {
        return args;
    }

    private Stream<String> evaluateArguments(Shell shell) {
        return args
                .stream()
                .map(a -> a.evaluate(shell))
                .map(Object::toString);
    }

    private final List<ValueExpression> args;

    private final String commandName;
}
