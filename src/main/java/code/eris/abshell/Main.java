package code.eris.abshell;

import code.eris.abshell.ast.CommandExpression;
import code.eris.abshell.ast.ExecutableExpression;
import code.eris.abshell.ast.Expression;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Shell shell = new Shell();
        ExecutionEnvironment env = new ExecutionEnvironment();

        ExecutableExpression command = new CommandExpression("whoami", List.of());
        command.execute(shell, env);
    }
}
