package by.teradapter.forallcommands;

import by.teradapter.forallcommands.command.MainCommand;
import by.teradapter.forallcommands.util.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class ForAllCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Config.load(getConfig());

        getCommand("forall").setExecutor(new MainCommand(this));

    }
}
