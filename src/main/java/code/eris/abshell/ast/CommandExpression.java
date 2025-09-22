package code.eris.abshell.ast;

import code.eris.abshell.ExecutionEnvironment;
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
    public int execute(Shell shell, ExecutionEnvironment environment) {
        ProcessBuilder pb = new ProcessBuilder(new ArrayList<>());

        Stream<String> args = evaluateArguments(shell, environment);
        pb.command().add(commandName);
        pb.command().addAll(args.toList());

        environment.configureProcessBuilder(pb);
        
        try {
            Process proc = pb.start();
            proc.waitFor();
            return proc.exitValue();
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

    private Stream<String> evaluateArguments(Shell shell, ExecutionEnvironment environment) {
        return args
                .stream()
                .map(a -> a.evaluate(shell, environment))
                .map(Object::toString);
    }

    private final List<ValueExpression> args;

    private final String commandName;
}
