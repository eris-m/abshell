package code.eris.abshell.ast;

import code.eris.abshell.ExecutionEnvironment;
import code.eris.abshell.Shell;

public class VariableExpression implements ValueExpression {
    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Object evaluate(Shell shell, ExecutionEnvironment environment) {
        Object value = shell.getScope().getVariable(name);

        return value == null ? "" : value;
    }

    private String name;
}
