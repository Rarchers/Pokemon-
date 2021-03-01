package com.rarcher.pokedex

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.rarcher.pokedex.ui.theme.*


class MainActivity : AppCompatActivity() {
    @ExperimentalUnsignedTypes
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {

        }
    }
}
}
