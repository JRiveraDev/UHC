package org.runnerer.pvp;

import org.bukkit.Bukkit;
import org.runnerer.pvp.melee.MeleeListener;
import org.runnerer.pvp.ranged.BowListener;
import org.runnerer.uhc.UHC;

public class PVPManager
{

    public static void registerEngines(UHC plugin)
    {
        Bukkit.getPluginManager().registerEvents(new BowListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new MeleeListener(), plugin);
    }
}
