package io.github.rank.birthday

import io.github.rank.birthday.utils.isCandle
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class BirthDayListener(private val plugin: BirthDayPlugin) : Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK) {
            if (event.item == navigator) {
                event.player.openNavigator(plugin)
            } else if (event.item == match) {
                if (event.clickedBlock?.type?.isCandle() == true) {
                    event.clickedBlock?.type = Material.FIRE
                } else {
                    event.isCancelled = true
                }
            }
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        event.quitMessage(Component.text("${ChatColor.WHITE}[ ${ChatColor.RED}- ${ChatColor.WHITE}] " +
                "${ChatColor.RED}${event.player.name} ${ChatColor.WHITE}님이 퇴장하였습니다.")) // Quit
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.inventory.clear()
        event.player.inventory.setItem(8, navigator)

        if (birthDayPeople.contains(event.player.name)) {
            event.joinMessage(Component.text("${ChatColor.RED}${event.player.name} ${ChatColor.WHITE}님이 접속하셨습니다!\n" +
                    "${ChatColor.GREEN}생일 축하합니다!")) // Birthday people Join Message
            return
        }

        event.joinMessage(Component.text("${ChatColor.WHITE}[ ${ChatColor.GREEN}+ ${ChatColor.WHITE}] " +
                "${ChatColor.GREEN}${event.player.name} ${ChatColor.WHITE}님이 접속하였습니다.")) // Join
    }
}