package io.github.rank.birthday.utils

import org.bukkit.Material

fun Material.isCandle(): Boolean = Candle.valuesList().contains(this.toCandle())

fun Material.toCandle(): Candle? {
    Candle.values().forEach {
        if (it.toMaterial() == this) return it
    }

    return null
}

enum class Candle(private val material: Material) {
    NORMAL(Material.CANDLE),
    WHITE(Material.WHITE_CANDLE),
    ORANGE(Material.ORANGE_CANDLE),
    MAGENTA(Material.MAGENTA_CANDLE),
    LIGHT_BLUE(Material.LIGHT_BLUE_CANDLE),
    YELLOW(Material.YELLOW_CANDLE),
    LIME(Material.LIME_CANDLE),
    PINK(Material.PINK_CANDLE),
    GRAY(Material.GRAY_CANDLE),
    LIGHT_GRAY(Material.LIGHT_GRAY_CANDLE),
    CYAN(Material.CYAN_CANDLE),
    PURPLE(Material.PURPLE_CANDLE),
    BLUE(Material.BLUE_CANDLE),
    BROWN(Material.BROWN_CANDLE),
    GREEN(Material.GREEN_CANDLE),
    RED(Material.RED_CANDLE),
    BLACK(Material.BLACK_CANDLE);

    fun toMaterial(): Material = material

    companion object {
        fun valuesList(): List<Candle> = listOf(NORMAL, WHITE, ORANGE, MAGENTA, LIGHT_BLUE, YELLOW, LIME, PINK, GRAY, LIGHT_GRAY,
            CYAN, PURPLE, BLUE, BROWN, GREEN, RED, BLACK)
    }
}