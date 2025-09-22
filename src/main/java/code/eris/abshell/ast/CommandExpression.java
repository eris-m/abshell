package code.eris.abshell.ast;

import code.eris.abshell.ExecutionEnvironment;
import code.eris.abshell.Shell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandExpression implements ExecutableExpression {
    public CommandExpression() {
        this("", new ArrayList<>());
    }
    
    public CommandExpression(String commandName, List<String> args) {
        this.commandName = commandName;
        this.args = args;
    }

    @Override
    public int execute(Shell shell, ExecutionEnvironment environment) {
        ProcessBuilder pb = new ProcessBuilder();
        populateCommandList(pb.command());
        
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

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    private void populateCommandList(List<String> list) {
        list.add(commandName);
        list.addAll(args);
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    private List<String> args;

    private String commandName;
}
