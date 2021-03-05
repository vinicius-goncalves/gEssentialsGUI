package goncalviz.essentialsgui.versionmanager.actionbar;

import goncalviz.essentialsgui.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class VersionManagerActionBar {

    /*
    Em breve com Reflection;
     */
    private static VersionManagerActionBarInterface versionManagerActionBarInterface;
    private static ConsoleCommandSender consoleCommandSender = Bukkit.getConsoleSender();
    private static Utils utils = new Utils();

    public static boolean setupVersionManagerActionBar() {

        String versionNMS;

        try {
            versionNMS = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        }catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }

        if(versionNMS.equals("v1_8_R3")) {
            versionManagerActionBarInterface = new VersionManagerActionBar_v1_8_R3();

        }else if(versionNMS.equals("v1_9_R1")) {
            versionManagerActionBarInterface = new VersionManagerActionBar_v1_9_R1();

        }else {

            consoleCommandSender.sendMessage(utils.getPrefix() + utils.withColor("&cO plugin nao suporta essa versao."));

        }

        return true;
    }

    public VersionManagerActionBarInterface getVersionManagerActionBarInterface() {
        return versionManagerActionBarInterface;

    }
}
