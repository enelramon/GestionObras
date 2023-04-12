package com.ucne.gestionobrasapp.ui.proyectos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos

import kotlinx.coroutines.launch
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InfoDetallesProyectoScreen(viewModel: ProyectosApiViewModel = hiltViewModel(), navController: NavController) {

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth())
    {
        Spacer(Modifier.height(20.dp))
        Icon(
            imageVector = Icons.TwoTone.ArrowCircleLeft,
            contentDescription = null,
            tint = Color(0xCDFFA185),
            modifier = Modifier.align(Alignment.Start)
                .size(50.dp, 50.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(ScreenModuleProyectos.DetallesProyecto.route)
                    }
                }
        )
        Spacer(modifier = Modifier.padding(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {
            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.ArrowDropUp,
                tint = Color(0xCDFFA185),
                contentDescription = null,

                )
            Text(
                text = ": Icono para abrir menu de opciones.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {

            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.PersonAdd,
                tint = Color(0xFF94B4F5),
                contentDescription = null,

                )
            Text(
                text = ": Icono para añadir trabajores.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )

        }

        //---------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {

            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.StickyNote2,
                tint = Color(0xFFBC78FF),
                contentDescription = null,

                )
            Text(
                text = ": Icono para consultar nomina.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )

        }

        //---------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {

            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.PriceChange,
                tint = Color(0xFFB8E698),
                contentDescription = null,

                )
            Text(
                text = ": Icono para realizar adelantos.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )

        }

        //---------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {

            Spacer(modifier = Modifier.padding(34.dp))
            Icon(
                modifier = Modifier.size(40.dp, 40.dp).weight(1f),
                imageVector = Icons.TwoTone.Savings,
                tint = Color(0xFFFF94A0),
                contentDescription = null,

                )
            Text(
                text = ": Icono para realizar pagos.", fontSize = 12.sp,
                modifier = Modifier.weight(7f),
                color = Color(0xFFD1808C)
            )

        }

        //---------------------------


        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = " Nota: Necesitas conección a internet para el funcionamiento de la aplicacion.", fontSize = 10.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )
        Spacer(modifier = Modifier.padding(8.dp))
        Divider(Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = " Final Project, Aplicada 2.", fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )

        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "by Michael Mora, Jose angel & Frank Yeury ©", fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }
    //
}