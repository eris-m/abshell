package code.eris.abshell;

import code.eris.abshell.commandlets.CommandletDiscoverer;
import code.eris.abshell.commandlets.CommandletService;

import java.io.IOException;
import java.util.*;

public class Shell {
    private static class DefaultProcessCreator implements ProcessCreator {
        @Override
        public ProcessCreator withCommand(String command) {
            if (processBuilder.command().isEmpty()) 
                processBuilder.command().add(command);
            else 
                processBuilder.command().set(0, command);

            return this;
        }

        @Override
        public ProcessCreator withArgs(List<String> args) {
            List<String> command = processBuilder.command();
            String commandName = command.isEmpty() ? "" : command.getFirst();

            command.clear();
            command.add(commandName);
            command.addAll(args);

            return this;
        }

        @Override
        public ProcessCreator withInputRedirect(ProcessBuilder.Redirect redirect) {
            processBuilder.redirectInput(redirect);
            return this;
        }

        @Override
        public ProcessCreator withOutputRedirect(ProcessBuilder.Redirect redirect) {
            processBuilder.redirectOutput(redirect);
            return this;
        }

        @Override
        public ProcessCreator withErrorRedirect(ProcessBuilder.Redirect redirect) {
            processBuilder.redirectError(redirect);
            return this;
        }

        @Override
        public Process start() throws IOException {
            return processBuilder.start();
        }


        private final ProcessBuilder processBuilder = new ProcessBuilder(new ArrayList<>());
    }

    private class DefaultCommandletDiscoverer implements CommandletDiscoverer {
        public DefaultCommandletDiscoverer() {
            serviceLoader = ServiceLoader.load(CommandletService.class);
        }

        @Override
        public void reload() {
            serviceLoader.reload();
            populateCommandlets();
        }

        @Override
        public Iterator<CommandletService> getCommandlets() {
            return serviceLoader.iterator();
        }

        private final ServiceLoader<CommandletService> serviceLoader;
    }

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
        return new DefaultCommandletDiscoverer();
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
