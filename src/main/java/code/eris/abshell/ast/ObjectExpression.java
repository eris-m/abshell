package code.eris.abshell.ast;

import code.eris.abshell.Shell;

public class ObjectExpression implements ValueExpression {
    public ObjectExpression(Object object) {
        this.object = object;
    }
    
    @Override
    public Object evaluate(Shell shell) {
        return object;
    }
    
    private Object object;
}
