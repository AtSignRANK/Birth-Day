package io.github.rank.birthday

import net.kyori.adventure.text.Component
import net.projecttl.inventory.gui.gui
import net.projecttl.inventory.gui.utils.InventoryType
import org.bukkit.ChatColor
import org.bukkit.Instrument
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

val birthDayPeople = listOf<String>() // BirthDay People! You may change it!

val match = ItemStack(Material.FLINT_AND_STEEL).apply {
    editMeta {
        it.displayName(Component.text("${ChatColor.YELLOW}성냥"))
        it.addItemFlags(*ItemFlag.values())
    }
}

val navigator = ItemStack(Material.COMPASS).apply {
    editMeta {
        it.displayName(Component.text("${ChatColor.YELLOW}네비게이터"))
        it.addItemFlags(*ItemFlag.values())
    }
}

val pluginMaker = listOf("ASR") // Don't change it!
val mapMaker = listOf<String>() // You may change it!

fun Player.openNavigator(plugin: BirthDayPlugin) {
    this.gui(plugin, InventoryType.CHEST_9, Component.text("Navigator")) {
        slot(0, ItemStack(Material.FLINT_AND_STEEL).apply {
            editMeta {
                it.displayName(Component.text("${ChatColor.RED}성냥 받기")) // Get Match
                it.lore(listOf(Component.text("${ChatColor.GREEN}/match")))
            }
        }) {
            player.inventory.addItem(match)
        }

        slot(1, ItemStack(Material.MUSIC_DISC_STRAD).apply {
            editMeta {
                it.displayName(Component.text("${ChatColor.RED}노래 듣기")) // Listen Music (Happy Birthday to you)
                it.lore(listOf(Component.text("${ChatColor.GREEN}/music")))
            }
        }) {
            player.playMusic(plugin, Instrument.PIANO)
        }

        slot(2, ItemStack(Material.LOOM).apply {
            editMeta {
                it.displayName(Component.text("${ChatColor.RED}피아노")) // Piano
                it.lore(listOf(Component.text("${ChatColor.GREEN}/piano")))
            }
        }) {
            player.playPiano(plugin)
        }

        // Inventory GUI have Bug! I issue it!
    }
}

class BirthDayPlugin : JavaPlugin() {
    override fun onEnable() {
        server.run {
            pluginManager.registerEvents(BirthDayListener(this@BirthDayPlugin), this@BirthDayPlugin)
            getPluginCommand("성냥")?.setExecutor(BirthDayCommand(this@BirthDayPlugin))
            getPluginCommand("navigator")?.setExecutor(BirthDayCommand(this@BirthDayPlugin))
            getPluginCommand("maker")?.setExecutor(BirthDayCommand(this@BirthDayPlugin))
            getPluginCommand("music")?.setExecutor(BirthDayCommand(this@BirthDayPlugin))
            getPluginCommand("piano")?.setExecutor(BirthDayCommand(this@BirthDayPlugin))
        }
    }
}