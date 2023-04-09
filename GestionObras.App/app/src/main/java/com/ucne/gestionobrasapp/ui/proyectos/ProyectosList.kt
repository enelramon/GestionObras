package com.ucne.gestionobrasapp.ui.proyectos

import android.R.attr.fontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.gestionobrasapp.R
import com.ucne.gestionobrasapp.data.remote.dto.ProyectosDto
import com.ucne.gestionobrasapp.ui.theme.Shapes
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleAcercade
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProyectosListScreen(navController: NavController, viewModel: ProyectosApiViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Proyectos", fontWeight = FontWeight.Bold);

            })
        },


        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.clip(Shapes.extraLarge),
                containerColor = Color(0xFF94B4F5),
                onClick = {
                    navController.navigate(ScreenModuleProyectos.Proyectos.route)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color(0xFFFFFFFF),
                    contentDescription = "Nuevo proyecto",
                    modifier = Modifier
                )

            }
        },

        ) {
        it
        val uiState by viewModel.uiState.collectAsState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ProyectoListBody(navController = navController, uiState.proyectos, Onclick = {}, viewModel)
        }
    }
}


@Composable
fun  ProyectoListBody(
    navController: NavController, proyectoList: List< ProyectosDto>, Onclick: ( ProyectosDto) -> Unit,
    viewModel:  ProyectosApiViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(proyectoList) {  proyectos ->
                TicketRow(navController = navController,  proyectos, viewModel)
            }
        }
    }
}


@Composable
fun TicketRow(
    navController: NavController,  proyecto:  ProyectosDto, viewModel:  ProyectosApiViewModel = hiltViewModel()
) {
    Spacer(modifier = Modifier.padding(10.dp))

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { navController.navigate(ScreenModuleProyectos.DetallesProyecto.route) }) // Le falta asignarle el id
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopEnd)
            ) {

                Text(
                    text = proyecto.descripcion,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(7f)
                )

                Icon(
                    imageVector = Icons.TwoTone.RoomPreferences,
                    tint = Color(0xCDFFA185),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .size(40.dp, 40.dp)
                )

            }
        }
        Divider(Modifier.fillMaxWidth())
    }


    Spacer(modifier = Modifier.padding(100.dp))
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo App"

    )
    Spacer(modifier = Modifier.padding(4.dp))
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.TwoTone.Lightbulb,
            tint = Color(0x8DFFEB36),
            contentDescription = "Icon information",
            modifier = Modifier
                .size(20.dp, 20.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(ScreenModuleAcercade.Acercade.route)
                    }
                }
        )
    }

}
