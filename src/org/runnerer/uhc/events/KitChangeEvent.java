package org.runnerer.uhc.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.runnerer.uhc.kit.Kit;

public class KitChangeEvent extends Event
{

    private static final HandlerList handlers = new HandlerList();
    private Kit kit;

    private boolean cancelled = false;

    public KitChangeEvent(Kit kit)
    {
        this.kit = kit;
    }

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public Kit getKit()
    {
        return this.kit;
    }

    public void setCancelled(boolean cancel)
    {
        cancelled = cancel;
    }

    public boolean isCancelled()
    {
        return cancelled;
    }
}
