package code.eris.abshell.ast;

import code.eris.abshell.Shell;

public interface ExecutableExpression extends Expression {
    public abstract int execute(Shell shell);


}
