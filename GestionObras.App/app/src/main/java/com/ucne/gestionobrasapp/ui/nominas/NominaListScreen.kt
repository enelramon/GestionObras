package com.ucne.gestionobrasapp.ui.nominas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.twotone.ArrowCircleLeft
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.gestionobrasapp.data.remote.dto.NominasDto
import com.ucne.gestionobrasapp.ui.personas.PersonasApiViewModel
import com.ucne.gestionobrasapp.ui.theme.Shapes
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleNominas
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NominalistScreen(
    viewModel: NominasApiViewModel = hiltViewModel(),
    navController: NavController
) {
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
                    navController.navigate(ScreenModuleNominas.Nominas.route)
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

        }
    }

}
