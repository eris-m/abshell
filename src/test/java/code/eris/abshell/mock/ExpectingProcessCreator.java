package code.eris.abshell.mock;

import code.eris.abshell.ProcessCreator;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpectingProcessCreator implements ProcessCreator {
    public ExpectingProcessCreator(String command, List<String> args) {
        expectedCommand = command;
        expectedArgs = args;
    }
    
    public void setExpectedArgs(List<String> expectedArgs) {
        this.expectedArgs = expectedArgs;
    }

    public void setExpectedCommand(String expectedCommand) {
        this.expectedCommand = expectedCommand;
    }

    @Override
    public ProcessCreator withCommand(String command) {
        givenCommand = command;
        return this;
    }

    @Override
    public ProcessCreator withArgs(List<String> args) {
        givenArgs = args;
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

    public void assertCorrect() {
        assertAll(
                () -> assertEquals(expectedCommand, givenCommand),
                () -> assertEquals(expectedArgs, givenArgs)
        );
    }

    private String expectedCommand;
    private String givenCommand;

    private List<String> expectedArgs;
    private List<String> givenArgs;

    // TODO: redirects
}
