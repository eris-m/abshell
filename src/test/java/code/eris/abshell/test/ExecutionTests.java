package code.eris.abshell.test;

import code.eris.abshell.mock.ExpectingProcessCreator;
import code.eris.abshell.mock.MockShell;
import code.eris.abshell.Shell;
import code.eris.abshell.ast.CommandExpression;
import code.eris.abshell.ast.ExecutableExpression;
import code.eris.abshell.ast.ObjectExpression;
import code.eris.abshell.ast.ValueExpression;
import code.eris.abshell.mock.TestCommandlet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author eris
 */
public class ExecutionTests {
    @Test
    public void testSingle() {
        String whoAmI = "whoami";

	    Shell shell = new MockShell(new ExpectingProcessCreator(whoAmI, List.of()));

        ExecutableExpression ex = new CommandExpression(whoAmI, List.of());
        ex.execute(shell);

        ExpectingProcessCreator pc = (ExpectingProcessCreator)shell.getProcessBuilder();
        pc.assertCorrect();
    }

    @Test
    public void testSimpleArgs() {
        String ls = "ls";
        List<String> argsStrings = List.of("Hello!");
        List<ValueExpression> argsExprs = argsStrings
                .stream()
                .map(s -> (ValueExpression)new ObjectExpression(s))
                .toList();
        
        
        Shell shell = new MockShell(new ExpectingProcessCreator(ls, argsStrings));
        ExecutableExpression ex = new CommandExpression(ls, argsExprs);
        ex.execute(shell);

        ExpectingProcessCreator pc = (ExpectingProcessCreator)shell.getProcessBuilder();
        pc.assertCorrect();
    }

    @Test
    public void testVariableArgs() {
        String cmd = "echo";
        List<Variable> vars = List.of(
                new Variable("MY_VARIABLE", "A"),
                new Variable("VAR2", "B")
        );

        List<String> varValuesStr = vars
                .stream()
                .map(Variable::value)
                .toList();
        List<ValueExpression> varValuesExpr = varValuesStr
                .stream()
                .map(v -> (ValueExpression)new ObjectExpression(v))
                .toList();

        Shell shell = new MockShell(new ExpectingProcessCreator(cmd, varValuesStr));
        
        for (Variable v : vars) {
            shell.getScope().setVariable(v.name(), v.value());
        }
        
        ExecutableExpression ex = new CommandExpression(cmd, varValuesExpr);
        ex.execute(shell);

        ExpectingProcessCreator pc = (ExpectingProcessCreator)shell.getProcessBuilder();
        pc.assertCorrect();
    }

    @Test
    void testCommandlet() {
        String name = "testCommandlet";
        ExecutableExpression ex = new CommandExpression(name, List.of());

        Shell sh = new Shell();
        int i = ex.execute(sh);

        assertEquals(0, i);
        assertTrue(TestCommandlet.hasExecuted);
    }
}

record Variable(String name, String value) {

}
