package code.eris.abshell.commandlets;

import java.util.Iterator;

public interface CommandletDiscoverer {
    void reload();
    Iterator<CommandletService> getCommandlets();
}
