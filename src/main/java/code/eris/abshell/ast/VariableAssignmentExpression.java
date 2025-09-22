package code.eris.abshell.ast;

import code.eris.abshell.ExecutionEnvironment;
import code.eris.abshell.Shell;

/**
 * Assignment of a variable as a standalone expression.
 */
public class VariableAssignmentExpression implements ExecutableExpression {
    public VariableAssignmentExpression(String name, ValueExpression to) {
        this.name = name;
        this.to = to;
    }

    @Override
    public int execute(Shell shell, ExecutionEnvironment environment) {
        Object value = to.evaluate(shell, environment);

        shell.getScope().setVariable(name, value);

        return 0;
    }

    public String getName() {
        return name;
    }

    public ValueExpression getTo() {
        return to;
    }

    private final String name;
    private final ValueExpression to;
}
