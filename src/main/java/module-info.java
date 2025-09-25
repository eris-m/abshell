module abshell.abshell {
    requires java.base;

    exports code.eris.abshell;
    exports code.eris.abshell.ast;
    requires org.junit.jupiter.api;
}