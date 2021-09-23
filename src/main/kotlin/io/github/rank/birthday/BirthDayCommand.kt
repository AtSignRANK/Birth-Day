package io.github.rank.birthday

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Instrument
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class BirthDayCommand(private val plugin: BirthDayPlugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        when (command.name) {
            "성냥" -> sender.inventory.addItem(match)

            "navigator" -> sender.openNavigator(plugin)

            "maker" -> {
                Bukkit.broadcast(Component.text("${ChatColor.GREEN}MAKER"))
                Bukkit.broadcast(Component.text("${ChatColor.AQUA}PLUGIN"))
                pluginMaker.forEach {
                    Bukkit.broadcast(Component.text("${ChatColor.AQUA}$it"))
                }
                Bukkit.broadcast(Component.text("${ChatColor.YELLOW}MAP"))
                mapMaker.forEach {
                    Bukkit.broadcast(Component.text("${ChatColor.YELLOW}$it"))
                }
                Bukkit.broadcast(Component.text("${ChatColor.RED}Thanks! And Happy Birthday!!"))
            }

            "music" -> sender.playMusic(plugin, Instrument.PIANO)

            "piano" -> sender.playPiano(plugin)
        }

        return false
    }
}