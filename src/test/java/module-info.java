import code.eris.abshell.mock.TestCommandlet;

module abshell.test {
    uses code.eris.abshell.commandlets.CommandletService;
    requires abshell.abshell;
    requires org.junit.jupiter;
    requires org.junit.jupiter.api;
    opens code.eris.abshell.test to org.junit.platform.commons;
//    exports code.eris.abshell.commandlets;

    provides code.eris.abshell.commandlets.CommandletService with TestCommandlet;
}

