package com.rarcher.pokedex.pokeDex.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider

import com.rarcher.pokedex.pokeDetail.activity.PokeDetail
import com.rarcher.pokedex.pokeDetail.activity.pokeLabel
import com.rarcher.pokedex.pokeDetail.model.Pokemon
import com.rarcher.pokedex.pokeDetail.model.pokemons
import com.rarcher.pokedex.pokeDex.activity.ui.theme.PokedexTheme
import com.rarcher.pokedex.viewModel.AmbientViewModel

class PokeDex : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                window.statusBarColor = 0x04000000
                val ambientViewModel: AmbientViewModel = ViewModelProvider(this).get(AmbientViewModel::class.java)
                list(this, ambientViewModel)

            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun list(context: Context, viewModel: AmbientViewModel) {
    val list = pokemons
   /* LazyColumnFor(
        items = list,
        contentPadding = PaddingValues(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 20.dp)
    ) { pokemon: Pokemon ->
        ItemCard(pokemon = pokemon, context = context, viewModel = viewModel)


    }*/
    
    LazyVerticalGrid(cells = GridCells.Fixed(2), content = {
        items(list){pokemon->
            ItemCard(pokemon = pokemon, context = context, viewModel = viewModel)
        }
    })
    
    
   /* ScrollableColumn() {
        list.forEach {
            ItemCard(pokemon = it, context = context, viewModel = viewModel)
        }
    }*/
}

@Composable
fun ItemCard(pokemon: Pokemon, context: Context, viewModel: AmbientViewModel) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = pokemon.color(),
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(onClick = {
                //viewModel.setSelectedPoke(pokemon = pokemon)
                val bundle = Bundle()
                bundle.putSerializable("pokemon", pokemon)
                context.startActivity(
                    Intent(context, PokeDetail::class.java)
                        .putExtra("poke", bundle)
                )
            })
    ) {
        Box {

            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 8.dp, start = 12.dp)
            ) {
                pokemon.name?.let {
                    Text(
                        text = it,
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
                pokemon.typeOfPokemon?.let {
                    Column() {
                        pokeLabel(pokemon.typeOfPokemon)
                    }
                }
            }

            pokemon.image?.let { painterResource(id = it) }
                ?.let {
                    Image(
                        painter = it,
                        contentDescription = pokemon.name,
                        modifier = Modifier
                            .size(72.dp)
                            .align(Alignment.BottomEnd)
                            .padding(end = 5.dp, bottom = 5.dp)
                    )
                }


        }
    }
}

