package code.eris.abshell.commandlets;

import code.eris.abshell.Shell;
import code.eris.abshell.error.ExecutionException;
import code.eris.abshell.error.IncorrectArgumentsException;

import java.util.List;

public class SetCommandlet implements CommandletService {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public int execute(Shell shell, List<Object> arguments) throws ExecutionException {
        if (arguments.size() != 2) {
            String message = String.format("expected 2 arguments, got %d", arguments.size());
            throw new IncorrectArgumentsException(message);
        }

        String name = arguments.getFirst().toString();
        Object value = arguments.get(1);

        shell.getScope().setVariable(name, value);

        return 0;
    }
}
