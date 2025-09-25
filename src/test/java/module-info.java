module abshell.test {
    requires abshell.abshell;
    requires org.junit.jupiter;
    requires org.junit.jupiter.api;
    opens code.eris.abshell.test to org.junit.platform.commons;
}

