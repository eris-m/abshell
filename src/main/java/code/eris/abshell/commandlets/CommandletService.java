package code.eris.abshell.commandlets;

import code.eris.abshell.Shell;
import code.eris.abshell.error.ExecutionException;

import java.util.List;

public interface CommandletService {
    String getName();
    int execute(Shell shell, List<Object> arguments) throws ExecutionException;
}
