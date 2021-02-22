package goncalviz.essentialsgui.files;

import goncalviz.essentialsgui.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigFile {

    public static File file;
    public static FileConfiguration fileConfiguration;

    public void createNewFileConfiguration() {
        File newFile = new File("plugins/gEssentialsGUI/configuracao.yml");
        if (!newFile.exists()) {
            try {
                Main.getPlugin(Main.class).saveResource("configuracao.yml", false);
            }catch(Exception ignored) {

            }
        }
    }

    public static FileConfiguration getFileConfiguration() {
        if (fileConfiguration == null) {
            file = new File("plugins/gEssentialsGUI/configuracao.yml");
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
