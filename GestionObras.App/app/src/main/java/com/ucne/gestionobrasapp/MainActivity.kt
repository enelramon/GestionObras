package com.ucne.gestionobrasapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucne.gestionobrasapp.ui.adelantos.AdelantosScreen
import com.ucne.gestionobrasapp.ui.nominas.NominaScreen
import com.ucne.gestionobrasapp.ui.pagos.PagosScreen
import com.ucne.gestionobrasapp.ui.personas.PersonaScreen
import com.ucne.gestionobrasapp.ui.personas.PersonasListScreen
import com.ucne.gestionobrasapp.ui.proyectos.DetallesProyectoScreen
import com.ucne.gestionobrasapp.ui.proyectos.NuevoProyectoScreen
import com.ucne.gestionobrasapp.ui.proyectos.ProyectosListScreen
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
                        startDestination = ScreenModuleProyectos.DetallesProyecto.route
                    ) {

                        composable(ScreenModuleStart.Start.route) {
                            MainScreen(navController = navController)
                        }

                        composable(ScreenModuleProyectos.ProyectoList.route) {
                            ProyectosListScreen(navController = navController)
                        }

                        composable(ScreenModuleProyectos.Proyectos.route) {
                            NuevoProyectoScreen(navController = navController)
                        }

                        composable(ScreenModuleProyectos.DetallesProyecto.route) {
                            DetallesProyectoScreen(navController = navController) // Le falta asignarle el id
                        }

                        composable(ScreenModulePersonas.Personas.route) {
                            PersonaScreen(navController = navController) // Le falta asignarle el id
                        }

                        composable(ScreenModuleAdelantos.Adelantos.route) {
                            AdelantosScreen(navController = navController) // Le falta asignarle el id
                        }

                        composable(ScreenModulePagos.Pagos.route) {
                            PagosScreen(navController = navController) // Le falta asignarle el id
                        }

                        composable(ScreenModuleNominas.Nominas.route) {
                            NominaScreen(navController = navController) // Le falta asignarle el id
                        }
                    }
                }
            }
        }
    }
}
