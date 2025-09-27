package code.eris.abshell;

import code.eris.abshell.commandlets.CommandletDiscoverer;
import code.eris.abshell.commandlets.CommandletService;

import java.util.Iterator;
import java.util.ServiceLoader;

class DefaultCommandletDiscoverer implements CommandletDiscoverer {
    private final Shell shell;

    public DefaultCommandletDiscoverer(Shell shell) {
        this.shell = shell;
        serviceLoader = ServiceLoader.load(CommandletService.class);
    }

    @Override
    public void reload() {
        serviceLoader.reload();
        shell.populateCommandlets();
    }

    @Override
    public Iterator<CommandletService> getCommandlets() {
        return serviceLoader.iterator();
    }

    private final ServiceLoader<CommandletService> serviceLoader;
}
