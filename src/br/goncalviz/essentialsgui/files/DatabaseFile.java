package br.goncalviz.essentialsgui.files;

import br.goncalviz.essentialsgui.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class DatabaseFile {

    private File file;
    private FileConfiguration fileConfiguration;

    public void createNewDatabaseFile() {
        File newFile = new File("plugins/gEssentialsGUI/database.yml");
        if (!newFile.exists()) {
            try {
                Main.getPlugin(Main.class).saveResource("database.yml", false);
            } catch(Exception e) {

            }
        }
    }


    public FileConfiguration getDatabaseFile() {
        if (fileConfiguration == null) {
            file = new File("plugins/gEssentialsGUI/database.yml");
            fileConfiguration = YamlConfiguration.loadConfiguration(file);
        }

        return fileConfiguration;
    }

    public void saveDatabaseFile() {
        try {
            getDatabaseFile().save(file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
