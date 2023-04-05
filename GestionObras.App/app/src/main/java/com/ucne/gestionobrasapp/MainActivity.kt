package com.ucne.gestionobrasapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucne.gestionobrasapp.ui.personas.PersonasListScreen
import com.ucne.gestionobrasapp.ui.personas.PersonasScreen
import com.ucne.gestionobrasapp.ui.theme.GestionObrasAppTheme
import com.ucne.gestionobrasapp.util.MainScreen
import com.ucne.gestionobrasapp.util.navigation.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestionObrasAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenModuleProyectos.Start.route
                    ) {
                        composable(ScreenModuleProyectos.Start.route) {
                            MainScreen(navController = navController)
                        }

                        composable(ScreenModulePersonas.PersonasList.route) {
                            PersonasListScreen(navController = navController) {
                                    id -> navController.navigate(ScreenModulePersonas.PersonasList.route + "/${id}")
                            }
                        }

                        composable(
                            ScreenModulePersonas.Personas.route + "/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { capturar ->
                            var personaId = capturar.arguments?.getInt("id") ?: 0

                            PersonasScreen(personaId = personaId, navController = navController) {
                                navController.navigate(ScreenModulePersonas.PersonasList.route)
                            }
                        }
                    }
                }
            }
        }
    }
}