/*
 *  Created by Filip P. on 2/2/15 11:13 PM.
 */

package me.pauzen.alphacore.updater;

import me.pauzen.alphacore.Core;
import me.pauzen.alphacore.utils.reflection.Nullify;
import me.pauzen.alphacore.utils.reflection.Registrable;
import org.bukkit.Bukkit;

public class UpdateManager implements Registrable {

    @Nullify
    private static UpdateManager manager;

    public static void register() {
        manager = new UpdateManager();
    }

    public static UpdateManager getManager() {
        return manager;
    }

    public UpdateManager() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getCore(), () -> {
            for (UpdateType updateType : UpdateType.values())
                if (updateType.elapsed())
                    new UpdateEvent(updateType).call();
        }, 1L, 1L);
    }

}
