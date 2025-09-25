package code.eris.abshell.ast;

import code.eris.abshell.Shell;

public class VariableExpression implements ValueExpression {
    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Object evaluate(Shell shell) {
        Object value = shell.getScope().getVariable(name);

        return value == null ? "" : value;
    }

    private String name;
}
