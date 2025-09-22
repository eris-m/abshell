package code.eris.abshell.ast;

import code.eris.abshell.ExecutionEnvironment;
import code.eris.abshell.Shell;

import java.util.List;

public interface ExecutableExpression extends Expression {
    public abstract int execute(Shell shell, ExecutionEnvironment environment);


}
