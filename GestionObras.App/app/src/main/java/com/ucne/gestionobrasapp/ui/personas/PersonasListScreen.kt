package com.ucne.gestionobrasapp.ui.personas

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.ucne.gestionobrasapp.data.remote.dto.PersonasDto
import com.ucne.gestionobrasapp.ui.proyectos.ProyectosApiViewModel
import com.ucne.gestionobrasapp.ui.theme.Shapes
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleAcercade
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleAdelantos
import com.ucne.gestionobrasapp.util.navigation.ScreenModulePersonas
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonasListScreen(
    navController: NavController,
    viewModel: PersonasApiViewModel = hiltViewModel(),
    onPersonaClick: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.padding(20.dp))
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Lista de Personas", fontSize = 30.sp,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .clickable {
                                    scope.launch {
                                        navController.navigate(ScreenModulePersonas.PersonasList.route)
                                    }
                                }
                        )
                    }
                )
            }
        ) {
            val uiState by viewModel.uiState.collectAsState()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
            {
                PersonasListBody(uiState.personas) {
                    onPersonaClick(it)
                }
            }
        }
    }
}

@Composable
fun PersonasListBody(personasList: List<PersonasDto>, onPersonaClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(personasList) { personas ->
                PersonasRow(personas)
                {
                    onPersonaClick(it)
                }
            }
        }
    }
}

@Composable
fun PersonasRow(personas: PersonasDto, onPersonaClick: (Int) -> Unit) {
    val viewModel = viewModel<PersonasApiViewModel>()

    Spacer(modifier = Modifier.padding(5.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(9.dp)
            .clickable(
                onClick = { onPersonaClick(personas.personaId) }
            )
    ) {
        var swipeThreshold by remember { mutableStateOf(130.dp) }

        val leftSwipe = SwipeAction(
            onSwipe = {
                // Incrementar valor de swipeThreshold al hacer swipe
                swipeThreshold += 10.dp
                if (swipeThreshold >= 140.dp) {
                    viewModel.deletePersonas(personas.personaId)
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
                    viewModel.deletePersonas(personas.personaId)
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
                        text = personas.nombres, fontSize = 25.sp,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(7f)
                    )

                    Icon(
                        imageVector = Icons.TwoTone.PriceChange,
                        tint = Color(0xFF272727),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(20.dp, 20.dp)
                            .weight(9f)
                            .clickable(onClick = { onPersonaClick(personas.personaId) })
                    )

                    Icon(
                        imageVector = Icons.TwoTone.Phone,
                        tint = Color(0xCD161616),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(20.dp, 20.dp)
                    )

                    Text(
                        text = " "+ personas.telefono, fontSize = 14.sp,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color(0xD0000000)
                    )
                }
            }
        }
    }
}