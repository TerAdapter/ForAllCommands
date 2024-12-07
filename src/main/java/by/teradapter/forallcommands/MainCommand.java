package by.teradapter.forallcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("forallcommands.use")) {
            sender.sendMessage("§cУ вас нет прав!");
            return true;
        }

        if (args.length == 0) {
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

        sender.sendMessage("§b▶ §fКоманда §b/" + commandToExecute + " §fвыполнена для всех игроков!");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        completions.add("%all%");

        return completions;
    }
}