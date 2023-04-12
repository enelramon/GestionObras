package com.ucne.gestionobrasapp.ui.nominas


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ucne.gestionobrasapp.data.remote.dto.NominasDto
import com.ucne.gestionobrasapp.ui.theme.Shapes
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleAcercade
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleNominas
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NominasListScreen(
    viewModel: NominasApiViewModel = hiltViewModel(),
    navController: NavController,
    onNominaClick: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()

    Column( modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.padding(20.dp))
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Lista de Nominas", fontSize = 30.sp,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                        )
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
                                        navController.navigate(ScreenModuleProyectos.DetallesProyecto.route)
                                    }
                                }
                        )
                        Spacer(modifier = Modifier.padding(20.dp))
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.clip(Shapes.extraLarge),
                    containerColor = Color(0xFF94B4F5),
                    onClick = {
                        navController.navigate(ScreenModuleNominas.NuevaNominas.route)
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
            )
            {
                NominaListBody(uiState.nominas) {
                    onNominaClick(it)
                }
            }
        }
    }
}


@Composable
fun NominaListBody(nominaList: List<NominasDto>, onNominaClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(nominaList) {nomina ->
                NominaRow(nomina)
                {
                    onNominaClick(it)
                }
            }
        }
    }
}

@Composable
fun NominaRow(nomina: NominasDto, onNominaClick: (Int) -> Unit) {

    Spacer(modifier = Modifier.padding(5.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .wrapContentSize(Alignment.Center)
                .border(
                    2.dp, Color(0xA88E24AA),
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Card(
                shape = RoundedCornerShape(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable(onClick = { onNominaClick(nomina.nominaId) })
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.CenterEnd)
                    ) {
                        Text(
                            text = nomina.personaId.toString(), fontSize = 25.sp,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF000000),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(7f)
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.TopStart),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = nomina.estado + " ", fontSize = 20.sp,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color(0xC3303030),
                            modifier = Modifier
                                .weight(7f)
                                .wrapContentSize(Alignment.CenterEnd)
                        )
                        Icon(
                            imageVector = when (nomina.estado) {
                                "En proceso" -> {
                                    Icons.TwoTone.Refresh
                                }
                                "Finalizado" -> {
                                    Icons.TwoTone.DownloadDone
                                }
                                else -> {
                                    Icons.TwoTone.AddCircle
                                }
                            },
                            contentDescription = nomina.estado,
                            tint = when (nomina.estado) {
                                "En proceso" -> {
                                    Color.Blue
                                }
                                "Finalizado" -> {
                                    Color.Green
                                }
                                else -> {
                                    Color.Gray
                                }
                            },
                            modifier = Modifier
                                .size(20.dp)
                                .wrapContentSize(Alignment.CenterEnd)
                        )
                    }
                }
            }
        }
    }
}