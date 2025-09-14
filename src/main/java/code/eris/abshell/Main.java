package code.eris.abshell;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Shell shell = new Shell();
        Expression command = new CommandExpression("/usr/bin/tree", List.of());
    }
}
