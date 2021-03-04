package goncalviz.essentialsgui.versionmanager.actionbar;

import org.bukkit.Bukkit;

public class VersionManagerActionBar {

    private static VersionManagerActionBarInterface versionManagerActionBarInterface;

    public static boolean setupVersionManagerActionBar() {

        String versionNMS;

        try {
            versionNMS = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        }catch(ArrayIndexOutOfBoundsException e) {
            Bukkit.getConsoleSender().sendMessage("Erro ao carregar o versionamento de versoes.");
            e.printStackTrace();
            return false;
        }

        Bukkit.getConsoleSender().sendMessage("Your version " + versionNMS);

        if(versionNMS.equals("v1_8_R3")) {
            versionManagerActionBarInterface = new VersionManagerActionBar_v1_8_R3();

        }else if(versionNMS.equals("v1_9_R1")) {
            versionManagerActionBarInterface = new VersionManagerActionBar_v1_9_R1();

        }else {

            Bukkit.getConsoleSender().sendMessage("O plugin nao suporta essa versao.");
        }

        return true;
    }

    public VersionManagerActionBarInterface getVersionManagerActionBarInterface() {
        return versionManagerActionBarInterface;

    }
}
