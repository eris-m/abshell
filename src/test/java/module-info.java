module abshell.test {
    requires abshell;
    requires org.junit.jupiter;
    opens code.eris.abshell.test to org.junit.platform.commons;
}

