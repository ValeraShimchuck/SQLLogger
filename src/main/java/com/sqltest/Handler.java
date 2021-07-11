package com.sqltest;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Date;

public class Handler implements Listener {
    private Main plugin;
    public Handler(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Date data = new Date();
        Long time = data.getTime();
        String playerName = e.getPlayer().getName();
        String loc = e.getPlayer().getLocation().getBlockX()+" "+e.getPlayer().getLocation().getBlockY()+" "+e.getPlayer().getLocation().getBlockZ();
        String action = "PlayerJoin";
        plugin.saveData(time,playerName,action,loc);

    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Date date = new Date();
        Long time = date.getTime();
        String playerName = e.getPlayer().getName();
        String loc = e.getPlayer().getLocation().getBlockX()+" "+e.getPlayer().getLocation().getBlockY()+" "+e.getPlayer().getLocation().getBlockZ();
        String action = "PlayerQuit";
        plugin.saveData(time,playerName,action,loc);
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Date date = new Date();
        Long time = date.getTime();
        String playerName = e.getPlayer().getName();
        String info = e.getBlock().getX()+" "+e.getBlock().getY()+" "+e.getBlock().getZ()+"|"+String.valueOf(e.getBlock().getWorld().getName())+"|"+e.getBlock().getType();
        String action = "PlayerBlockBreak";
        plugin.saveData(time,playerName,action,info);

    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Date date = new Date();
        Long time = date.getTime();
        String playerName = e.getPlayer().getName();
        String info = e.getBlock().getX()+" "+e.getBlock().getY()+" "+e.getBlock().getZ()+"|"+String.valueOf(e.getBlock().getWorld().getName())+"|"+e.getBlock().getType();
        String action = "PlayerBlockPlace";
        plugin.saveData(time,playerName,action,info);
    }
}
