package code.eris.abshell.mock;

import code.eris.abshell.Shell;
import code.eris.abshell.commandlets.CommandletService;
import code.eris.abshell.error.ExecutionException;

import java.util.List;

public class TestCommandlet implements CommandletService {
    @Override
    public String getName() {
        return "testCommandlet";
    }

    @Override
    public int execute(Shell shell, List<Object> arguments) throws ExecutionException {
        hasExecuted = true;
        return 0;
    }

    public static boolean hasExecuted = false;
}
