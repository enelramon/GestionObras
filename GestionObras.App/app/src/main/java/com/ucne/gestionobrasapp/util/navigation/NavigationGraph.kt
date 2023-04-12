package com.ucne.gestionobrasapp.util.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucne.gestionobrasapp.ui.adelantos.AdelantosScreen
import com.ucne.gestionobrasapp.ui.nominas.NominaScreen
import com.ucne.gestionobrasapp.ui.pagos.PagosScreen
import com.ucne.gestionobrasapp.ui.personas.PersonaScreen
import com.ucne.gestionobrasapp.ui.proyectos.*
import kotlinx.coroutines.delay
import com.ucne.gestionobrasapp.R
import com.ucne.gestionobrasapp.ui.nominas.NominasListScreen
import com.ucne.gestionobrasapp.ui.nominas.NuevaNominaScreen
import com.ucne.gestionobrasapp.ui.personas.PersonasListScreen

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalAnimationApi
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenModuleProyectos.Splash.route
    ) {
        composable(ScreenModuleProyectos.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(ScreenModuleProyectos.ProyectoList.route) {
            Naviagtions(navController = navController) {
                navController.navigate(ScreenModuleProyectos.DetallesProyecto.route)
            }
        }

        composable(ScreenModulePersonas.PersonasList.route) {
            PersonasListScreen(navController = navController) { id ->
                navController.navigate(ScreenModulePersonas.PersonasList.route + "/${id}")
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

        composable(route = ScreenModuleAdelantos.Adelantos.route){
            AdelantosScreen(navController = navController) {
                navController.navigate(ScreenModuleAdelantos.Adelantos.route)
            }
        }

        composable(ScreenModuleNominas.NuevaNominas.route) {
            NuevaNominaScreen(navController = navController)
        }

        composable(ScreenModulePagos.Pagos.route) {
            PagosScreen(navController = navController) // Le falta asignarle el id
        }

        composable(ScreenModuleNominas.NominasList.route) {
            NominasListScreen(navController = navController){ id ->
                navController.navigate(ScreenModuleNominas.Nominas.route + "/${id}")
            }
        }

        composable(route = ScreenModuleNominas.Nominas.route + "/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { capturar ->
            val nominaId = capturar.arguments?.getInt("id") ?: 0
            NominaScreen(nominaId = nominaId, navController = navController){
                navController.navigate(ScreenModuleNominas.Nominas.route)
            }
        }

        composable(ScreenModuleAcercade.InfoInicio.route) {
            InfoInicioScreen(navController = navController)
        }

        composable(ScreenModuleAcercade.InfoDetallesProyecto.route) {
            InfoDetallesProyectoScreen(navController = navController)
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate(ScreenModuleProyectos.ProyectoList.route)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        )
    }
    //
}

