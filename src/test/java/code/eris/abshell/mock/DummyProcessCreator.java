package code.eris.abshell.mock;

import code.eris.abshell.ProcessCreator;
import code.eris.abshell.mock.MockProcess;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author eris
 */
public class DummyProcessCreator implements ProcessCreator {

    @Override
    public ProcessCreator withCommand(String command) {
        return this;
    }

    @Override
    public ProcessCreator withArgs(List<String> args) {
        return this;
    }

    @Override
    public ProcessCreator withInputRedirect(ProcessBuilder.Redirect redirect) {
        return this;
    }

    @Override
    public ProcessCreator withOutputRedirect(ProcessBuilder.Redirect redirect) {
        return this;
    }

    @Override
    public ProcessCreator withErrorRedirect(ProcessBuilder.Redirect redirect) {
        return this;
    }

    @Override
    public Process start() throws IOException {
        return new MockProcess();
    }
    
}
