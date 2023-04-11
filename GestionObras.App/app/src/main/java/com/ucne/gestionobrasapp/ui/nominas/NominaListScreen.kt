package com.ucne.gestionobrasapp.ui.nominas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowCircleLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.gestionobrasapp.data.remote.dto.NominasDto
import com.ucne.gestionobrasapp.ui.personas.PersonasApiViewModel
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch


@Composable
fun NominaScreen(
    viewModel: NominasApiViewModel = hiltViewModel(),
    navController: NavController
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.CenterStart)
    ) {

        Spacer(modifier = Modifier.padding(20.dp))
        Icon(
            imageVector = Icons.TwoTone.ArrowCircleLeft,
            contentDescription = null,
            tint = Color(0xCDFFA185),
            modifier = Modifier
                .size(50.dp, 50.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(ScreenModuleProyectos.DetallesProyecto.route)
                    }
                }
        )
        // Falta hacer la nomina
    }

}
