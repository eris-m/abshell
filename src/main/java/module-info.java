import code.eris.abshell.commandlets.CommandletService;
import code.eris.abshell.commandlets.SetCommandlet;

module abshell.abshell {
    requires java.base;

    exports code.eris.abshell;
    exports code.eris.abshell.ast;
    exports code.eris.abshell.commandlets;
    exports code.eris.abshell.error;

    uses CommandletService;

    provides CommandletService with
            SetCommandlet;
}