package org.runnerer.uhc.engine.border;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.runnerer.uhc.GameManager;
import org.runnerer.uhc.UHC;

public class UHCBorder
{
    private static World world = Bukkit.getWorld("world");

    public static void startBorderShrink()
    {
        UHCBorder.startBorder();

        UHC.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(UHC.getInstance(), new Runnable()
        {
            public void run()
            {
                GameManager.instance.announce("The border is shrinking!");
                UHCBorder.borderTwo();
            }
        }, 20 * 60 * 7  * 1L);

        UHC.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(UHC.getInstance(), new Runnable()
        {
            public void run()
            {
                GameManager.instance.announce("The border is shrinking!");
                UHCBorder.borderThree();
            }
        }, 20 * 60 * 7  * 2L);

        UHC.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(UHC.getInstance(), new Runnable()
        {
            public void run()
            {
                GameManager.instance.announce("The border is shrinking!");
                UHCBorder.borderThree();
            }
        }, 20 * 60 * 7  * 3L);

        UHC.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(UHC.getInstance(), new Runnable()
        {
            public void run()
            {
                GameManager.instance.announce("The border is shrinking!");
                UHCBorder.borderFour();
            }
        }, 20 * 60 * 7  * 4L);

        UHC.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(UHC.getInstance(), new Runnable()
        {
            public void run()
            {
                GameManager.instance.announce("The border is shrinking!");
                UHCBorder.borderFive();
            }
        }, 20 * 60 * 7  * 5L);
    }

    public static void startBorder()
    {
        WorldBorder border = world.getWorldBorder();
        border.setSize(800);
        border.setCenter(0.0, 0.0);
        border.reset();
    }

    public static void borderTwo()
    {
        World world = Bukkit.getWorld("UHC");

        WorldBorder border = world.getWorldBorder();
        border.setSize(600);
        border.setCenter(0.0, 0.0);
        border.reset();
    }

    public static void borderThree()
    {
        World world = Bukkit.getWorld("UHC");

        WorldBorder border = world.getWorldBorder();
        border.setSize(400);
        border.setCenter(0.0, 0.0);
        border.reset();
    }

    public static void borderFour()
    {
        World world = Bukkit.getWorld("UHC");

        WorldBorder border = world.getWorldBorder();
        border.setSize(200);
        border.setCenter(0.0, 0.0);
        border.reset();
    }

    public static void borderFive()
    {
        World world = Bukkit.getWorld("UHC");

        WorldBorder border = world.getWorldBorder();
        border.setSize(25);
        border.setCenter(0.0, 0.0);
        border.reset();
    }
}
