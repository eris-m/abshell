package code.eris.abshell.test;

import code.eris.abshell.Shell;
import code.eris.abshell.mock.DummyProcessCreator;
import code.eris.abshell.mock.MockShell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValueTests {
    @Test
    void testSimpleVariable() {
        String name = "MY_VARIABLE";
        String value = "foo bar baz";
        
        Shell sh = new MockShell(new DummyProcessCreator());
        sh.getScope().setVariable(name, value);
    
        assertEquals(value, sh.getScope().getVariable(name));
    }
}
