package com.onarandombox.multiverseinventories.listener;

import com.onarandombox.multiverseinventories.MultiverseInventories;
import me.drayshak.WorldInventories.WorldInventories;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import uk.co.tggl.pluckerpluck.multiinv.MultiInv;

/**
 * Server Listener for Multiverse Inventories for hooking/unhooking plugins for importing.
 */
public class MVIServerListener extends ServerListener {

    private MultiverseInventories plugin;

    public MVIServerListener(MultiverseInventories plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPluginEnable(PluginEnableEvent event) {
        try {
            if (event.getPlugin() instanceof MultiInv) {
                this.plugin.getImportManager().hookMultiInv((MultiInv) event.getPlugin());
            } else if (event.getPlugin() instanceof WorldInventories) {
                this.plugin.getImportManager().hookWorldInventories((WorldInventories) event.getPlugin());
            }
        } catch (NoClassDefFoundError ignore) {}
    }

    @Override
    public void onPluginDisable(PluginDisableEvent event) {
        try {
            if (event.getPlugin() instanceof MultiInv) {
                this.plugin.getImportManager().unHookMultiInv();
            } else if (event.getPlugin() instanceof WorldInventories) {
                this.plugin.getImportManager().unHookWorldInventories();
            }
        } catch (NoClassDefFoundError ignore) {}
    }
}
