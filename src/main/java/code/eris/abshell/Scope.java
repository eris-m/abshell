package code.eris.abshell;

import java.util.HashMap;

public class Scope {
    public Scope() {
        parent = null;
        vars = new HashMap<>();
    }
    
    public Scope(Scope parent) {
        this.parent = parent;
        vars = new HashMap<>();
    }
    
    public void setVariable(String name, Object value) {
        vars.put(name, value);
    }
    
    public Object getVariable(String name) {
        return vars.get(name);
    }
    
    public void unsetVariable(String name) {
        vars.remove(name);
    }
    
    public Scope createChildScope() {
        return new Scope(this);
    }
    
    /**
     * Gets the parent scope of this scope.
     * @return Parent scope, {@code null} if {@link #isRoot} is {@code true}
     */
    public Scope getParent() {
        return parent;
    }
    
    /**
     * @return If this scope is the root scope or not.
     */
    public boolean isRoot() {
        return parent == null;
    }
    
    /**
     * Parent scope, can be null.
     */
    private Scope parent;
    private HashMap<String, Object> vars;
}
