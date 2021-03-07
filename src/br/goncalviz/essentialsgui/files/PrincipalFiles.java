package br.goncalviz.essentialsgui.files;

import java.io.File;

public class PrincipalFiles {

    public void createNewFolder() {
        File newFolder = new File("plugins/gEssentialsGUI");
        if (!newFolder.exists()) {
            newFolder.mkdirs();

        }
    }
}
