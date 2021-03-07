package br.goncalviz.essentialsgui.files;

import br.goncalviz.essentialsgui.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessagesFiles {

    private File file;
    private FileConfiguration fileConfiguration;

    public void createNewMessagesFile() {
        File newFile = new File("plugins/gEssentialsGUI/messages.yml");
        if (!newFile.exists()) {
            Main.getPlugin(Main.class).saveResource("messages.yml", false);

        }
    }

    public FileConfiguration getFileConfiguration() {
        if (fileConfiguration == null) {
            file = new File("plugins/gEssentialsGUI/messages.yml");
            fileConfiguration = YamlConfiguration.loadConfiguration(file);
        }

        return fileConfiguration;

    }

    public void saveFileConfiguration() {
        try {
            getFileConfiguration().save(file);
        }catch(Exception ignored) {

        }
    }
}
