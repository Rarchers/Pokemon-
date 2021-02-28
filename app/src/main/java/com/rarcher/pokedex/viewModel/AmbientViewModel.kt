package com.rarcher.pokedex.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rarcher.pokedex.pokeDetail.model.Pokemon

class AmbientViewModel : ViewModel(){
    private val _selectedPoke = MutableLiveData<Pokemon>().also { it.value = Pokemon() }
    val selectPoke : LiveData<Pokemon> = _selectedPoke
    fun setSelectedPoke(pokemon: Pokemon){
        _selectedPoke.value = pokemon
    }
}