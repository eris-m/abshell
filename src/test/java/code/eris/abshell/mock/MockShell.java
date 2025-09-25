package code.eris.abshell.mock;

import code.eris.abshell.ProcessCreator;
import code.eris.abshell.Shell;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class MockShell extends Shell {
    public MockShell(ProcessCreator pc) {
        this.pc = pc;
    }

    @Override
    public ProcessCreator getProcessBuilder() {
        return pc;
    }

    ProcessCreator pc;
}
