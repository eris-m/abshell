package code.eris.abshell;

import code.eris.abshell.commandlets.CommandletDiscoverer;
import code.eris.abshell.commandlets.CommandletService;

import java.util.*;

public class Shell {
    public Shell() {
        populateCommandlets();
    }

    /**
     * @return A new process creator that the shell should use for creating processes.
     */
    public ProcessCreator getProcessBuilder() {
        return new DefaultProcessCreator();
    }

    protected CommandletDiscoverer getCommandletDiscoverer() {
        return new DefaultCommandletDiscoverer(this);
    }

    public HashMap<String, CommandletService> getCommandlets() {
        return commandlets;
    }
    
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

    protected void populateCommandlets() {
        commandlets.clear();

        CommandletDiscoverer discoverer = getCommandletDiscoverer();
        Iterator<CommandletService> commandletsIter = discoverer.getCommandlets();

        commandletsIter.forEachRemaining(c -> commandlets.put(c.getName(), c));
    }

    private final HashMap<String, CommandletService> commandlets = new HashMap<>();
    private Scope scope = new Scope();
}
