package com.rarcher.pokedex.pokeDetail.model

data class Stat(val label: String,
                val value: Int?,
                val max: Int = 100) {
    val process = 1f * (value ?: 0) / max
}