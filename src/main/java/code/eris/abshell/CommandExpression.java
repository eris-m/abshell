package code.eris.abshell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandExpression implements Expression {
    public CommandExpression() {
        commandName = "";
        args = new ArrayList<>();
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

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
    
    private void populateCommandList(List<String> list) {
        list.add(commandName);
        list.addAll(args);
    }
    
    private String commandName;
    private List<String> args;
}
