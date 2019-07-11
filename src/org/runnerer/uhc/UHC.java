package org.runnerer.uhc;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.runnerer.database.MySQL;
import org.runnerer.pvp.PVPManager;
import org.runnerer.uhc.engine.GameEngine;
import org.runnerer.uhc.engine.UHCGame;
import org.runnerer.uhc.lobby.LobbyManager;
import org.runnerer.uhc.spectator.SpectatorManager;

public class UHC extends JavaPlugin
{
    public static UHC instance;

    @Override
    public void onEnable()
    {
        instance = this;

        PVPManager pvpManager = new PVPManager();
        PVPManager.registerEngines(this);

        GameManager gameManager = new GameManager();
        registerEngine(new LobbyManager());
        registerEngine(new SpectatorManager());
        registerEngine(new GameEngine());

        gameManager.setGame(new UHCGame());

        new MySQL();
        try
        {
            MySQL.Instance.openConnection();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void registerEngine(Listener listener)
    {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public static UHC getInstance()
    {
        return instance;
    }
}
