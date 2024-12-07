package by.teradapter.forallcommands;

import org.bukkit.plugin.java.JavaPlugin;

public final class ForAllCommands extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("Используйте /fac <команда> чтобы отправить её для каждого онлайн игрока (пример: /fac msg Привет!)");

        getCommand("forall").setExecutor(new MainCommand());

    }
}
