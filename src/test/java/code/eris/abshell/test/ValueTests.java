package code.eris.abshell.test;

import code.eris.abshell.Shell;
import code.eris.abshell.ast.ExecutableExpression;
import code.eris.abshell.ast.ObjectExpression;
import code.eris.abshell.ast.ValueExpression;
import code.eris.abshell.ast.VariableAssignmentExpression;
import code.eris.abshell.ast.VariableExpression;
import code.eris.abshell.mock.DummyProcessCreator;
import code.eris.abshell.mock.MockShell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValueTests {
    private static Shell getShell() {
        return new MockShell(new DummyProcessCreator());
    }

    
    @Test
    void testSimpleVariable() {
        String name = "MY_VARIABLE";
        String value = "foo bar baz";
        
        Shell sh = getShell();
        sh.getScope().setVariable(name, value);
    
        assertEquals(value, sh.getScope().getVariable(name));
    }

    @Test
    void testVariableAssignment() {
        String name = "MY_VARIABLE";
        Object value = 100.0;

        Shell sh = getShell();
        ExecutableExpression expr = new VariableAssignmentExpression(
                name, 
                new ObjectExpression(value)
        );

        expr.execute(sh);

       Object got = sh.getScope().getVariable(name);
       assertEquals(value, got);
    }

    @Test
    void testVariableExpr(){
        String name = "VAR";
        Object value = new Object();
        
        Shell sh = getShell();
        sh.getScope().setVariable(name, value);
        
        ValueExpression ex = new VariableExpression(name);
        Object got = ex.evaluate(sh);

        assertEquals(value, got);
    }
}
