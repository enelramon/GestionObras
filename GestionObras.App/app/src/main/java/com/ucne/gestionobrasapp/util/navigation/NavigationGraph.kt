package com.ucne.gestionobrasapp.util.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucne.gestionobrasapp.ui.adelantos.AdelantosScreen
import com.ucne.gestionobrasapp.ui.nominas.NominaScreen
import com.ucne.gestionobrasapp.ui.nominas.NominalistScreen
import com.ucne.gestionobrasapp.ui.pagos.PagosScreen
import com.ucne.gestionobrasapp.ui.personas.PersonaScreen
import com.ucne.gestionobrasapp.ui.proyectos.*

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalAnimationApi
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenModuleProyectos.ProyectoList.route
    ) {
        composable(ScreenModuleProyectos.ProyectoList.route) {
            Naviagtions(navController = navController) {
                navController.navigate(ScreenModuleProyectos.DetallesProyecto.route)
            }
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

        composable(ScreenModuleNominas.NominasList.route) {
            NominalistScreen(navController = navController) // Le falta asignarle el id
        }
        composable(ScreenModuleNominas.Nominas.route) {
            NominaScreen(navController = navController) // Le falta asignarle el id
        }

        composable(ScreenModuleAcercade.Acercade.route) {
            AcercadeScreen(navController = navController)
        }
    }
}

