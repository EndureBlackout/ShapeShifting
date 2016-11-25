
package me.endureblackout.ss;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import net.md_5.bungee.api.ChatColor;

public class SSMain extends JavaPlugin implements CommandExecutor, Listener {

	public ArrayList<UUID> disguised = new ArrayList<UUID>();

	public void onEnable() {
		getCommand("wolf").setExecutor(this);

		Bukkit.getPluginManager().registerEvents(this, this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("wolf")) {
				MobDisguise wolf = new MobDisguise(DisguiseType.WOLF);

				if (!(disguised.contains(p.getUniqueId()))) {
					DisguiseAPI.disguiseToAll(p, wolf);
					p.sendMessage(ChatColor.GREEN + "You are now disguised as a wolf.");
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
					disguised.add(p.getUniqueId());
				} else {
					DisguiseAPI.undisguiseToAll(p);
					p.sendMessage(ChatColor.GREEN + "You are no longer disguised as a wolf.");
					p.removePotionEffect(PotionEffectType.SPEED);
					disguised.remove(p.getUniqueId());
				}
			}
		}

		return true;
	}

	@EventHandler
	public void onItemClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (e.getAction().toString().contains("RIGHT_CLICK") && p.getInventory().getItemInMainHand().getType().toString().equals("BONE")) {

			MobDisguise wolf = new MobDisguise(DisguiseType.WOLF);

			if (!disguised.contains(p.getUniqueId())) {
				DisguiseAPI.disguiseToAll(p, wolf);
				p.sendMessage(ChatColor.GREEN + "You are now diguised as a wolf.");
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
				disguised.add(p.getUniqueId());
			} else {
				DisguiseAPI.undisguiseToAll(p);
				p.sendMessage(ChatColor.GREEN + "You are no long disguised as a wolf.");
				p.removePotionEffect(PotionEffectType.SPEED);
				disguised.remove(p.getUniqueId());
			}
		}
	}
}
