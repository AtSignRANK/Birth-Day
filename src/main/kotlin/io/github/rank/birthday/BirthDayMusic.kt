package io.github.rank.birthday

import net.kyori.adventure.text.Component
import net.projecttl.inventory.gui.gui
import net.projecttl.inventory.gui.utils.InventoryType
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

const val oneForTick = 8L

fun Player.note(plugin: BirthDayPlugin, instrument: Instrument, note: Note, backja: Double, next: () -> Unit) {
    this.playNote(this.location, instrument, note)

    Bukkit.getScheduler().runTaskLater(plugin, Runnable {
        next.invoke()
    }, (oneForTick * backja).toLong())
}

fun Player.note(plugin: BirthDayPlugin, instrument: Instrument, note: Note, next: () -> Unit) {
    this.playNote(this.location, instrument, note)

    Bukkit.getScheduler().runTaskLater(plugin, Runnable {
        next.invoke()
    }, oneForTick)
}

fun Player.note(plugin: BirthDayPlugin, instrument: Instrument, note: Note, backja: Double) {
    this.playNote(this.location, instrument, note)

    Bukkit.getScheduler().runTaskLater(plugin, Runnable {}, (oneForTick * backja).toLong())
}

fun Player.note(plugin: BirthDayPlugin, instrument: Instrument, note: Note) {
    this.playNote(this.location, instrument, note)

    Bukkit.getScheduler().runTaskLater(plugin, Runnable {}, oneForTick)
}

fun Player.wait(plugin: BirthDayPlugin, backja: Double, next: () -> Unit) {
    Bukkit.getScheduler().runTaskLater(plugin, Runnable {
        next.invoke()
    }, (oneForTick * backja).toLong())
}

fun Player.playMusicPart1(plugin: BirthDayPlugin, instrument: Instrument, next: () -> Unit) {
    // 생일 축하합니다 한 옥타브 내리기

    this.note(plugin, instrument, Note.natural(0, Note.Tone.C), 1.5) {
        this.note(plugin, instrument, Note.natural(0, Note.Tone.C), 0.5) {
            this.note(plugin, instrument, Note.natural(0, Note.Tone.D)) {
                this.note(plugin, instrument, Note.natural(0, Note.Tone.C)) {
                    this.note(plugin, instrument, Note.natural(0, Note.Tone.F)) {
                        this.note(plugin, instrument, Note.natural(0, Note.Tone.E), 2.0) {
                            next.invoke()
                        }
                    }
                }
            }
        }
    }
}

fun Player.playMusicPart2(plugin: BirthDayPlugin, instrument: Instrument, next: () -> Unit) {
    this.note(plugin, instrument, Note.natural(0, Note.Tone.C), 1.5) {
        this.note(plugin, instrument, Note.natural(0, Note.Tone.C), 0.5) {
            this.note(plugin, instrument, Note.natural(0, Note.Tone.D)) {
                this.note(plugin, instrument, Note.natural(0, Note.Tone.C)) {
                    this.note(plugin, instrument, Note.natural(0, Note.Tone.G)) {
                        this.note(plugin, instrument, Note.natural(0, Note.Tone.F), 2.0) {
                            next.invoke()
                        }
                    }
                }
            }
        }
    }
}

fun Player.playMusicPart3(plugin: BirthDayPlugin, instrument: Instrument) {
    this.note(plugin, instrument, Note.natural(0, Note.Tone.C), 1.5) {
        this.note(plugin, instrument, Note.natural(0, Note.Tone.C), 0.5) {
            this.note(plugin, instrument, Note.natural(1, Note.Tone.C)) {
                this.note(plugin, instrument, Note.natural(0, Note.Tone.A)) {
                    this.note(plugin, instrument, Note.natural(0, Note.Tone.F)) {
                        this.note(plugin, instrument, Note.natural(0, Note.Tone.E)) {
                            this.note(plugin, instrument, Note.natural(0, Note.Tone.D)) {
                                this.note(plugin, instrument, Note.flat(0, Note.Tone.B), 1.5) {
                                    this.note(plugin, instrument, Note.flat(0, Note.Tone.B), 0.5) {
                                        this.note(plugin, instrument, Note.natural(0, Note.Tone.A)) {
                                            this.note(plugin, instrument, Note.natural(0, Note.Tone.F)) {
                                                this.note(plugin, instrument, Note.natural(0, Note.Tone.G)) {
                                                    this.note(plugin, instrument, Note.natural(0, Note.Tone.F), 2.0)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun Player.playMusic(plugin: BirthDayPlugin, instrument: Instrument) {
    playMusicPart1(plugin, instrument) {
        playMusicPart2(plugin, instrument) {
            playMusicPart3(plugin, instrument)
        }
    }
}

fun Player.playPiano(plugin: BirthDayPlugin) {
    val instrument = Instrument.PIANO
    val piano = listOf(ItemStack(Material.RED_CONCRETE).apply {
        editMeta {
            displayName(Component.text("${ChatColor.RED}도")) // C
            addItemFlags(*ItemFlag.values())
        }
    }, ItemStack(Material.ORANGE_CONCRETE).apply {
        editMeta {
            displayName(Component.text("${ChatColor.GOLD}레")) // D
            addItemFlags(*ItemFlag.values())
        }
    }, ItemStack(Material.YELLOW_CONCRETE).apply {
        editMeta {
            displayName(Component.text("${ChatColor.YELLOW}미")) // E
            addItemFlags(*ItemFlag.values())
        }
    }, ItemStack(Material.LIME_CONCRETE).apply {
        editMeta {
            displayName(Component.text("${ChatColor.GREEN}파")) // F
            addItemFlags(*ItemFlag.values())
        }
    }, ItemStack(Material.LIGHT_BLUE_CONCRETE).apply {
        editMeta {
            displayName(Component.text("${ChatColor.AQUA}솔")) // G
            addItemFlags(*ItemFlag.values())
        }
    }, ItemStack(Material.BLUE_CONCRETE).apply {
        editMeta {
            displayName(Component.text("${ChatColor.BLUE}라")) // A
            addItemFlags(*ItemFlag.values())
        }
    }, ItemStack(Material.PURPLE_CONCRETE).apply {
        editMeta {
            displayName(Component.text("${ChatColor.LIGHT_PURPLE}시")) // B
            addItemFlags(*ItemFlag.values())
        }
    }, ItemStack(Material.RED_CONCRETE).apply {
        editMeta {
            displayName(Component.text("${ChatColor.RED}도"))
            addItemFlags(*ItemFlag.values())
        }
    }, ItemStack(Material.ORANGE_CONCRETE).apply {
        editMeta {
            displayName(Component.text("${ChatColor.GOLD}레"))
        }
    })

    val pianoSound = listOf(
        Note.natural(0, Note.Tone.C),
        Note.natural(0, Note.Tone.D),
        Note.natural(0, Note.Tone.E),
        Note.natural(0, Note.Tone.F),
        Note.natural(0, Note.Tone.G),
        Note.natural(0, Note.Tone.A),
        Note.natural(0, Note.Tone.B),
        Note.natural(1, Note.Tone.C),
        Note.natural(1, Note.Tone.D)
    )

    this.gui(plugin, InventoryType.CHEST_9, Component.text("피아노")) {
        for (i in 0 until 9) {
            slot(i, piano[i]) {
                player.note(plugin, instrument, pianoSound[i])
            }
        }
    }
}