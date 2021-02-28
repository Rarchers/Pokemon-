package com.rarcher.pokedex.pokeDetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rarcher.pokedex.pokeDetail.model.Pokemon
import com.rarcher.pokedex.pokeDetail.model.Stat





@Composable
fun baseStates(pokemon: Pokemon){
    val stats = listOf(
            Stat("HP", pokemon.hp),
            Stat("攻击", pokemon.attack),
            Stat("防御", pokemon.defense),
            Stat("特殊攻击", pokemon.specialAttack),
            Stat("特殊防御", pokemon.specialDefense),
            Stat("速度", pokemon.speed),
            Stat("总计", pokemon.total, 600)
    )
    StatsTable(stats = stats)
}

@Composable
fun StatsTable(stats : List<Stat>){
    Column(modifier = Modifier.padding(start = 20.dp,end = 20.dp,top = 50.dp)) {
        stats.forEach {
            card(stat = it)
        }
    }
}

@Composable
fun card(stat: Stat) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = stat.label,modifier = Modifier.weight(0.5F))
        Text(text = stat.value.toString(),modifier = Modifier.weight(0.5F),
                style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Bold
                ))
        LinearProgressIndicator(progress = stat.process,color = Color.Red,
                modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1F))
    }
}



@Composable
fun AboutSection(pokemon: Pokemon) {
    pokemon.description?.let {
        Text(it)
    }
}