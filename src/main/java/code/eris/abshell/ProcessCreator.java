package code.eris.abshell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface to abstract the details of {@link ProcessBuilder}.
 * 
 * Allows for mocking.
 */
public interface ProcessCreator {
    ProcessCreator withCommand(String command);
    ProcessCreator withArgs(List<String> args);
    
    ProcessCreator withInputRedirect(ProcessBuilder.Redirect redirect);
    ProcessCreator withOutputRedirect(ProcessBuilder.Redirect redirect);
    ProcessCreator withErrorRedirect(ProcessBuilder.Redirect redirect);
    
    Process start() throws IOException;
    
    default ProcessCreator withInputRedirect(File file) {
        ProcessBuilder.Redirect redirect = ProcessBuilder.Redirect.from(file);
        return withInputRedirect(redirect);
    }
    
    default ProcessCreator withOutputRedirect(File file) {
        ProcessBuilder.Redirect redirect = ProcessBuilder.Redirect.to(file);
        return withOutputRedirect(redirect);
    }
    
    default ProcessCreator withErrorRedirect(File file) {
        ProcessBuilder.Redirect redirect = ProcessBuilder.Redirect.to(file);
        return withErrorRedirect(redirect);
    }
}
