package com.rarcher.pokedex.pokeDetail.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rarcher.pokedex.pokeDetail.activity.ui.theme.PokedexTheme
import com.rarcher.pokedex.pokeDetail.model.Pokemon
import com.rarcher.pokedex.pokeDetail.model.Sections
import com.rarcher.pokedex.pokeDetail.model.pokemons
import com.rarcher.pokedex.pokeDetail.ui.AboutSection
import com.rarcher.pokedex.pokeDetail.ui.baseStates
import com.rarcher.pokedex.ui.theme.labelColor
import com.rarcher.pokedex.viewModel.AmbientViewModel
import java.util.*

class PokeDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
               /* val viewModel:AmbientViewModel = viewModel()
                var pokemon: Pokemon? = null
                viewModel.selectPoke.observe(this, {
                    pokemon = it
                })*/
                val intent = intent
                val bundle = intent.getBundleExtra("poke")
                val pokemon :Pokemon = bundle?.getSerializable("pokemon") as Pokemon

                window.statusBarColor = when(pokemon?.typeOfPokemon?.get(0)?.toLowerCase(Locale.ROOT)){
                    "grass", "bug" -> 0xFF2CDAB1.toInt()
                    "fire" -> 0xFFF7786B.toInt()
                    "water", "fighting", "normal" ->0xFF58ABF6.toInt()
                    "electric", "psychic" -> 0xFFFFCE4B.toInt()
                    "poison", "ghost" -> 0xFF9F5BBA.toInt()
                    "ground", "rock" -> 0xFFCA8179.toInt()
                    "dark" -> 0xFF303943.toInt()
                    else -> 0xFF58ABF6.toInt()
                }

                Box(Modifier.fillMaxSize()) {
                    pokemon?.let { BackGround(pokemon = it) }
                    pokemon?.let { HeaderLeft(pokemon = it) }
                    pokemon?.let { HeaderRight(pokemon = it) }
                    pokemon?.let { CardContent(pokemon = it) }
                    pokemon?.let { PokemonImage(pokemon = it) }
                }
            }
            }
        }
    }


@Composable
fun BoxScope.HeaderLeft(pokemon: Pokemon) {
    Column(modifier = Modifier
        .align(Alignment.TopStart)
        .padding(top = 40.dp)
        .padding(start = 32.dp)) {
        androidx.compose.material.Text(text = pokemon.name?:"",style = TextStyle(color = Color.White,fontSize = 30.sp))
        pokemon.typeOfPokemon?.let {
            Row {
                pokeLabel(pokemon.typeOfPokemon)
            }
        }

    }
}


@Composable
fun BoxScope.HeaderRight(pokemon: Pokemon) {
    Column(modifier = Modifier
        .align(Alignment.TopEnd)
        .padding(top = 40.dp, end = 32.dp)) {
        pokemon.id?.let { androidx.compose.material.Text(text = it,style = TextStyle(fontSize = 28.sp,color = Color.White)) }
        pokemon.category?.let { androidx.compose.material.Text(text = it,style = TextStyle(fontSize = 14.sp,color = Color.White),modifier = Modifier.align(
            Alignment.CenterHorizontally)) }
    }
}


@Composable
fun pokeLabel(pokeLabels : List<String>?) {
    pokeLabels?.forEach {
        Surface(
            color = labelColor,
            shape = RoundedCornerShape(8.dp)
        ) {
            androidx.compose.material.Text(
                text = it,
                style = TextStyle(fontSize = 12.sp, color = Color.White),
                modifier = Modifier.padding(12.dp, 4.dp)
            )
        }
        Spacer(modifier = Modifier
            .width(8.dp)
            .height(8.dp))
    }
}


@Composable
fun BackGround(pokemon: Pokemon) {

    Surface(color = pokemon.color(),modifier = Modifier.fillMaxSize()) {

    }
}


@Composable
fun BoxScope.PokemonImage(pokemon: Pokemon) {
    pokemon.image?.let { painterResource(id = it) }?.let {
      /*  Image(bitmap = it, modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 140.dp)
            .size(200.dp))*/
        Image(painter = it, contentDescription = pokemon.name, modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 140.dp)
            .size(200.dp))
    }
}


@Composable
fun BoxScope.CardContent(pokemon: Pokemon) {
    val select = remember { mutableStateOf(0) }
    val titles = Sections.values().map { it.title }
    Surface(modifier = Modifier
        .fillMaxSize()
        .align(Alignment.TopCenter)
        .padding(top = 300.dp),
        shape = RoundedCornerShape(32.dp).copy(bottomStart = ZeroCornerSize, bottomEnd = ZeroCornerSize),
        color = Color.White) {
        Column() {
            Spacer(modifier = Modifier.padding(top = 33.dp))
            TabRow(selectedTabIndex = select.value, backgroundColor = Color.White) {
                titles.forEachIndexed { index, s ->
                    Tab(selected = index == select.value, onClick = { select.value = index }) {
                        Column() {
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                            androidx.compose.material.Text(text = s)
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                        }
                    }
                }
            }
            Box(Modifier.padding(top = 24.dp)) {
                when(select.value){
                    0 -> AboutSection(pokemon = pokemon)
                    1 -> baseStates(pokemon = pokemon)
                }
            }
        }
    }
}





