package goncalviz.essentialsgui.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigFile {

    private File file;
    private FileConfiguration fileConfiguration;

    public void createNewFile() {
        File newFile = new File("plugins/gEssentialsGUI/configuracao.yml");
        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
            }catch(Exception ignored) {

            }
        }
    }

    public FileConfiguration getFileConfiguration() {
        if (fileConfiguration == null) {
            file = new File("plugins/gEssentials/configuracao.yml");
            fileConfiguration = YamlConfiguration.loadConfiguration(file);
        }

        return fileConfiguration;
    }

    public void saveFileConfiguration() {
        try {
            getFileConfiguration().save(file);
        }catch(Exception e) {

        }
    }
}
