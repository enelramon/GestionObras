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
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch

import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NuevoProyectoScreen(
    viewModel: ProyectosApiViewModel = hiltViewModel(),
    navController: NavController
) {

    ProyectosBody(viewModel, Modifier.fillMaxWidth(), navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProyectosBody(
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


        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Registro de Proyecto", fontSize = 27.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.descripcion,
            onValueChange = viewModel::onDescripcionChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Description,
                    contentDescription = null,
                    tint = Color(0xFF94B4F5),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Descripcion") },
            isError = viewModel.descripcionError.isNotBlank(),
            trailingIcon = {
                if (viewModel.descripcionError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.descripcionError.isNotBlank()) {
            Text(
                text = viewModel.descripcionError,
                color = MaterialTheme.colorScheme.error
            )
        }

            ExtendedFloatingActionButton(
                modifier = Modifier
                    .size(124.dp, 124.dp)
                    .wrapContentSize(Alignment.Center),
                text = { Text("Guardar") },
                contentColor = Color(0xFFFFFFFF),
                containerColor = Color(0xFF3992CC),
                icon = { Icon(imageVector = Icons.TwoTone.Save, contentDescription = "Save") },
                onClick = {

                    if (viewModel.HayErroresRegistrando()) {
                        viewModel.descripcionError = ""
                        if (viewModel.descripcion.isBlank()) {
                            viewModel.descripcionError = "  Debe indicar el nombre del proyecto"
                        }


                    } else {
                        viewModel.postProyectos()
                        navController.navigate(ScreenModuleProyectos.ProyectoList.route)

                    }

                }
            )

        }
    }
