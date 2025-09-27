package code.eris.abshell.error;

import java.io.IOException;

public class IOExecutionException extends ExecutionException {
    public IOExecutionException(IOException ex) {
        super(ex.getMessage());
        this.ex = ex;
    }

    public IOException getIOException() {
        return ex;
    }

    private IOException ex;
}
