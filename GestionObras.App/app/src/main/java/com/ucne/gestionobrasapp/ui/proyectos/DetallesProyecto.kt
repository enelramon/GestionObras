package com.ucne.gestionobrasapp.ui.proyectos


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.gestionobrasapp.util.navigation.ScreenModulePersonas
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch

import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetallesProyectoScreen(
    viewModel: ProyectosApiViewModel = hiltViewModel(),
    navController: NavController
) {

    DetallesProyectoBody(viewModel, Modifier.fillMaxWidth(), navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetallesProyectoBody(
    viewModel: ProyectosApiViewModel, modifier: Modifier, navController: NavController
) {

    val scope = rememberCoroutineScope()
    var expanded by remember {
        mutableStateOf(false)
    }



    Column(modifier = Modifier.fillMaxWidth())
    {
        Spacer(Modifier.height(20.dp))
        Icon(
            imageVector = Icons.TwoTone.ArrowCircleLeft,
            contentDescription = null,
            tint = Color(0xCDFFA185),
            modifier = Modifier
                .align(Alignment.Start)
                .size(50.dp, 50.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(ScreenModuleProyectos.ProyectoList.route)
                    }
                }
        )

        //------------------------------------


        Spacer(modifier = Modifier.padding(10.dp))

        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.TopEnd)
                ) {
                    Text(
                        text = "Nombres", // Se debe mostrar los nombres de las personas lo puse asi  de ejemplo
                        //text = persona_Nombres,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .weight(7f)


                    )
                    Text(
                        text = "Adelanto",
                        //text = proyecto_Adelanto,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .weight(7f)

                    )

                    Text(
                        text = "Pago",
                        //text = proyecto_Pago,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .weight(7f)

                    )
                }
            }
        }


        //------------------------------------

        ExtendedFloatingActionButton(
            modifier = Modifier
                .size(124.dp, 124.dp)
                .wrapContentSize(Alignment.Center),
            text = { Text("Nomina") },
            contentColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFF7432B4),
            icon = { Icon(imageVector = Icons.TwoTone.StickyNote2, contentDescription = "route") },
            onClick = {

                //navController.navigate(ScreenModuleNomina.Nominas.route)

            }
        )

        //------------------------------------

        ExtendedFloatingActionButton(
            modifier = Modifier
                .size(124.dp, 124.dp)
                .wrapContentSize(Alignment.Center),
            text = { Text("Nueva Persona") },
            contentColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFF3992CC),
            icon = { Icon(imageVector = Icons.TwoTone.Person, contentDescription = "route") },
            onClick = {

                navController.navigate(ScreenModulePersonas.Personas.route)

            }
        )

        //------------------------------------

        ExtendedFloatingActionButton(
            modifier = Modifier
                .size(124.dp, 124.dp)
                .wrapContentSize(Alignment.Center),
            text = { Text("Adelantos") },
            contentColor = Color(0xFF353535),
            containerColor = Color(0xFFFFE858),
            icon = { Icon(imageVector = Icons.TwoTone.PriceChange, contentDescription = "route") },
            onClick = {

                //navController.navigate(ScreenModuleAdelantos.Adelantos.route)

            }
        )

        //------------------------------------

        ExtendedFloatingActionButton(
            modifier = Modifier
                .size(124.dp, 124.dp)
                .wrapContentSize(Alignment.Center),
            text = { Text("Pagos") },
            contentColor = Color(0xFF353535),
            containerColor = Color(0xFF8FE278),
            icon = { Icon(imageVector = Icons.TwoTone.LocalAtm, contentDescription = "route") },
            onClick = {

                //navController.navigate(ScreenModulePagos.Pagos.route)

            }
        )

    }
}