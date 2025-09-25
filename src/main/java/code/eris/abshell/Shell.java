package code.eris.abshell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Shell {
    private class DefaultProcessCreator implements ProcessCreator {
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
    
    /**
     * @return A new process creator that the shell should use for creating processes.
     */
    public ProcessCreator getProcessBuilder() {
        return new DefaultProcessCreator();
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
    
    private Scope scope = new Scope();
}
