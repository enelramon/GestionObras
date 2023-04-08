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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ucne.gestionobrasapp.data.remote.dto.PersonasDto
import com.ucne.gestionobrasapp.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonasListScreen(
    navController: NavController,
    viewModel: PersonasApiViewModel = hiltViewModel(),
    onPersonasClick: (Int) -> Unit
) {
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
                            "Lista de Personas", fontSize = 30.sp,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                        )
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {},
                    shape = Shapes.extraLarge
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Save",
                        modifier = Modifier.clip(Shapes.extraLarge)
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) {
            val uiState by viewModel.uiState.collectAsState()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
            {
                PersonasListBody(uiState.personas) {
                    onPersonasClick(it)
                }
            }
        }
    }
}

@Composable
fun PersonasListBody(personasList: List<PersonasDto>, onPersonasClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(personasList) {personas ->
                PersonasRow(personas)
                {
                    onPersonasClick(it)
                }
            }
        }
    }
}

@Composable
fun PersonasRow(personas: PersonasDto, onPersonasClick: (Int) -> Unit) {

    val viewModel: PersonasApiViewModel = hiltViewModel()
    val navController = rememberNavController()

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
                        .clickable(onClick = { onPersonasClick(personas.personaId) })
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.CenterEnd)
                    ) {
                        Text(
                            text = personas.nombres, fontSize = 25.sp,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF000000),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(7f)
                        )
                        Text(
                            text = personas.telefono, fontSize = 17.sp,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color(0xD0000000),
                            modifier = Modifier
                                .weight(1.5f)
                                .border(
                                    2.dp, Color(0x56808080),
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                    }
                }
            }
        }
    }
}