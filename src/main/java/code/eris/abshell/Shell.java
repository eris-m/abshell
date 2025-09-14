package code.eris.abshell;

public class Shell {
    public void enterScope() {
        scope = scope.createChildScope();
    }
    
    public void exitScope() {
        if (scope.isRoot()) {
            return;
        }
        
        scope = scope.getParent();
    }
    
    public Scope getScope() {
        return scope;
    }
    
    private Scope scope = new Scope();
}
