package com.ucne.gestionobrasapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import com.ucne.gestionobrasapp.ui.theme.GestionObrasAppTheme
import com.ucne.gestionobrasapp.util.navigation.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestionObrasAppTheme {
                NavigationGraph()
            }
        }
    }
}

