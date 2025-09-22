package code.eris.abshell.ast;

import code.eris.abshell.ExecutionEnvironment;
import code.eris.abshell.Shell;

public interface ValueExpression extends Expression {
    Object evaluate(Shell shell, ExecutionEnvironment environment);
}
