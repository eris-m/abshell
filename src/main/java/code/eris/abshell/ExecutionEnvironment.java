package code.eris.abshell;

public class ExecutionEnvironment {    
    public ProcessBuilder configureProcessBuilder(ProcessBuilder pb) {
        pb.inheritIO();
        
        if (inputRedirect != null)
            pb.redirectInput(inputRedirect);
        
        if (outputRedirect != null)
            pb.redirectOutput(outputRedirect);
        
        if (errorRedirect != null)
            pb.redirectError(errorRedirect);
        
        return pb;
    }
    
    private ProcessBuilder.Redirect inputRedirect = null;
    private ProcessBuilder.Redirect outputRedirect = null;
    private ProcessBuilder.Redirect errorRedirect = null;
}
