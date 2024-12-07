package by.teradapter.forallcommands.command;

import by.teradapter.forallcommands.util.Config;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MainCommand implements CommandExecutor, TabCompleter {

    private final Plugin plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("forallcommands.use")) {
            sender.sendMessage(Config.Messages.noPerm);
            return true;
        }

        if (args.length == 0) {
            plugin.reloadConfig();
            Config.load(plugin.getConfig());
            sender.sendMessage("§cПожалуйста, укажите команду");
            return true;
        }

        String commandToExecute = String.join(" ", args);

        if (commandToExecute.contains("%all%")) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                String modifiedCommand = commandToExecute.replace("%all%", player.getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), modifiedCommand);
            });
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandToExecute);
        }

        sender.sendMessage(Config.Messages.done.replace("%command%", commandToExecute)); //commandToExecute
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        completions.add("%all%");

        return completions;
    }
}