package code.eris.abshell;

import code.eris.abshell.ast.CommandExpression;
import code.eris.abshell.ast.ExecutableExpression;
import code.eris.abshell.ast.Expression;
import code.eris.abshell.ast.ObjectExpression;
import code.eris.abshell.ast.ValueExpression;
import code.eris.abshell.ast.VariableAssignmentExpression;
import code.eris.abshell.ast.VariableExpression;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Shell shell = new Shell();
        ExecutionEnvironment env = new ExecutionEnvironment();

        int retCode = getExpressions()
                .stream()
                .map(e -> e.execute(shell, env))
                .reduce((a, b) -> a + b)
                .orElse(0);
        
        System.exit(retCode);
    }
    
    private static List<ExecutableExpression> getExpressions() {
        return List.of(
                new VariableAssignmentExpression("var", new ObjectExpression("src")),
                new CommandExpression("ls", List.of(new VariableExpression("var")))
        );
    }
}
