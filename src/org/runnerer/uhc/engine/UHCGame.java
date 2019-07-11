package org.runnerer.uhc.engine;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.runnerer.core.common.utils.C;
import org.runnerer.core.common.utils.UtilBlock;
import org.runnerer.stats.Stats;
import org.runnerer.uhc.GameManager;
import org.runnerer.uhc.UHC;
import org.runnerer.uhc.engine.border.UHCBorder;
import org.runnerer.uhc.game.Game;
import org.runnerer.uhc.game.GameState;

public class UHCGame extends Game
{

    public UHCGame()
    {
        super("UHC", new String[] {C.Gray + "The border is closing in!", C.Gray + "Gain resources that are in the ", C.Gray + "crust of Earth and fight ", C.Gray + "until the end!" });

        getGameConfig().itemPickup = true;
        getGameConfig().itemDrop = true;
        getGameConfig().damagePvP = true;
        getGameConfig().blockPlace = true;
        getGameConfig().blockBreak = true;
        getGameConfig().damageEvP = true;
        getGameConfig().damageOther = true;
        getGameConfig().damage = true;
    }

    @Override
    public void runScoreboard()
    {
        GameManager.instance.getBoard().add("", 2);
        GameManager.instance.getBoard().add("The border is closing in!", 1);
    }

    @Override
    public void onStart()
    {
        GameManager.instance.announce("Don't die!");
        UHCBorder.startBorderShrink();
    }

    @EventHandler
    public void onDamageEvent(EntityDamageByEntityEvent event)
    {
        if (getGameState() != GameState.IN_PROGRESS) return;

        if (event.getEntity() instanceof Player && !(event.getDamager() instanceof Player))
        {
            Player player = (Player) event.getEntity();

            if (player.getHealth() - event.getDamage() >= 0)
            {
                event.setCancelled(true);
                player.setHealth(20);
                Stats.addGamesPlayed(player.getUniqueId().toString(), 1);
                Stats.addDeaths(player.getUniqueId().toString(), 1);
                if (getPlayingPlayers().size() >= 1)
                {
                    for (Player winner : getPlayingPlayers())
                    {
                        end(winner);
                        Stats.addWins(winner.getUniqueId().toString(), 1);
                        return;
                    }
                }
                dead(player);
            }
        }
    }

    @EventHandler
    public void statsAdd(EntityDamageByEntityEvent event)
    {
        if (getGameState() != GameState.IN_PROGRESS) return;

        if (event.getEntity() instanceof Player && (event.getDamager() instanceof Player))
        {
            Player player = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();
            if (player.getHealth() - event.getDamage() >= 0)
            {
                event.setCancelled(true);
                player.setHealth(20);
                Stats.addKills(damager.getUniqueId().toString(), 1);
            }
        }
    }

    @EventHandler
    public void preventHealthRegen(EntityRegainHealthEvent event)
    {
        if (event.getRegainReason() == RegainReason.SATIATED)
            event.setCancelled(true);
    }
}
