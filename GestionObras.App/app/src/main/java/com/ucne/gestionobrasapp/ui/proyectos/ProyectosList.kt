package com.ucne.gestionobrasapp.ui.proyectos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.twotone.RoomPreferences
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ucne.gestionobrasapp.data.remote.dto.ProyectosDto
import com.ucne.gestionobrasapp.ui.theme.Shapes
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Naviagtions(
    navController: NavController,
    viewModel: ProyectosApiViewModel = hiltViewModel(),
    onProyectoClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    if (uiState.proyectos.isNullOrEmpty()) {
        NuevoProyectoScreen(navController = navController)
    } else {
        ProyectosListScreen(navController = navController, onProyectoClick = onProyectoClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProyectosListScreen(
    navController: NavController,
    viewModel: ProyectosApiViewModel = hiltViewModel(),
    onProyectoClick: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Listado de Proyectos",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.wrapContentSize(Alignment.TopCenter)
                            .clickable {
                            scope.launch {
                                navController.navigate(ScreenModuleProyectos.ProyectoList.route)
                            }
                        }
                    )
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
        val uiState by viewModel.uiState.collectAsState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ProyectoBody(uiState.proyectos) {
                onProyectoClick(it)
            }
        }
    }
}

@Composable
fun ProyectoBody(
    proyectoList: List<ProyectosDto>, onProyectoclick: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(proyectoList) { proyectos ->
                ProyectosRow(proyectos) {
                    onProyectoclick(it)
                }
            }
        }
    }
}

@Composable
fun ProyectosRow(
    proyectos: ProyectosDto,
    onProyectoClick: (Int) -> Unit
) {
    val viewModel = viewModel<ProyectosApiViewModel>()

    Spacer(modifier = Modifier.padding(5.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(9.dp)
            .clickable(
                onClick = { onProyectoClick(proyectos.proyectoId!!) }
            )
    ) {
        var swipeThreshold by remember { mutableStateOf(130.dp) }

        val leftSwipe = SwipeAction(
            onSwipe = {
                // Incrementar valor de swipeThreshold al hacer swipe
                swipeThreshold += 10.dp
                if (swipeThreshold >= 140.dp) {
                    viewModel.deleteProyectos(proyectos.proyectoId)
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete",
                    modifier = Modifier.padding(16.dp),
                    tint = Color.White
                )
            },
            background = Color.Red
        )

        val rithSwipe = SwipeAction(
            onSwipe = {
                // Incrementar valor de swipeThreshold al hacer swipe
                swipeThreshold += 10.dp
                if (swipeThreshold >= 140.dp) {
                    viewModel.deleteProyectos(proyectos.proyectoId)
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete",
                    modifier = Modifier.padding(16.dp),
                    tint = Color.White
                )
            },
            background = Color.Red
        )

        SwipeableActionsBox(
            swipeThreshold = swipeThreshold,
            startActions = listOf(leftSwipe),
            endActions = listOf(rithSwipe)
        ) {

            Card(
                shape = RoundedCornerShape(1.dp),
                colors = CardDefaults.elevatedCardColors(),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.CenterEnd)
                        .padding(20.dp)
                ) {
                    Text(
                        text = proyectos.descripcion,
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
        }
    }
}

