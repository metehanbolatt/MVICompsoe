package com.metehanbolat.mvicompsoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.metehanbolat.mvicompsoe.favorite.FavoriteScreen
import com.metehanbolat.mvicompsoe.ui.theme.MVICompsoeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVICompsoeTheme {
                FavoriteScreen(
                    onNavigateToMyList = {}
                )
            }
        }
    }
}


val dummySku = ""
val dummyId = ""