package by.teradapter.forallcommands.util;

import lombok.experimental.UtilityClass;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

@UtilityClass
public class Config {

    public static void load(FileConfiguration file) {
        final var messagesSection = file.getConfigurationSection("messages");
        if (messagesSection == null) {
            throw new IllegalStateException("Config.yml messages section is null");
        }
        parseMessages(messagesSection);
    }

    private static void parseMessages(ConfigurationSection section) {
        Messages.noPerm = section.getString("no-permission");
        Messages.done = section.getString("done");
        Messages.reloaded = section.getString("reloaded");
    }

    public static class Messages {
        public static String noPerm;
        public static String done;
        public static String reloaded;
    }
}
