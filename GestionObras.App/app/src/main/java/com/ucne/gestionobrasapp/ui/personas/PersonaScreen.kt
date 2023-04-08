package com.ucne.gestionobrasapp.ui.personas

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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonaScreen(
    viewModel: PersonasApiViewModel = hiltViewModel(),
    navController: NavController
) {
    PersonasBody(viewModel, Modifier.fillMaxWidth(), navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersonasBody(
    viewModel: PersonasApiViewModel, modifier: Modifier, navController: NavController
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
            text = "Registro de Personas", fontSize = 27.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.nombres,
            onValueChange = viewModel::onNombresChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Person,
                    contentDescription = null,
                    tint = Color(0xFF94B4F5),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Nombre") },
            isError = viewModel.nombresError.isNotBlank(),
            trailingIcon = {
                if (viewModel.nombresError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.nombresError.isNotBlank()) {
            Text(
                text = viewModel.nombresError,
                color = MaterialTheme.colorScheme.error
            )
        }

        //-------------------------------------------------

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.telefono,
            onValueChange = viewModel::onTelefonoChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Phone,
                    contentDescription = null,
                    tint = Color(0xFF1F1F1F),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Telefono") },
            isError = viewModel.telefonoError.isNotBlank(),
            trailingIcon = {
                if (viewModel.telefonoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.telefonoError.isNotBlank()) {
            Text(
                text = viewModel.telefonoError,
                color = MaterialTheme.colorScheme.error
            )
        }

        //-------------------------------------------------

        //Tipo de trabajo
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { expanded = true },
            value = viewModel.tiposTrabajo,
            enabled = false, readOnly = true,
            onValueChange = viewModel::onTrabajosChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.ContentPasteGo,
                    contentDescription = null,
                    tint = Color(0xFFF89945),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            isError = viewModel.tiposTrabajoError.isNotBlank(),
            label = { Text("Seleccionar trabajo") },
            trailingIcon = {
                if (viewModel.tiposTrabajoError.isNotBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.ArrowDropDown, contentDescription = "error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                if (viewModel.tiposTrabajoError.isBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.ArrowDropDown, contentDescription = "error",
                        tint = Color(0xFF8A8A8A)
                    )
                }

            }
        )

        if (viewModel.tiposTrabajoError.isNotBlank()) {
            Text(
                text = viewModel.tiposTrabajoError,
                color = MaterialTheme.colorScheme.error,

                )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)

        ) {
            viewModel.tiposDeTrabajo.forEach { opcion ->
                DropdownMenuItem(
                    text = {
                        Text(text = opcion, textAlign = TextAlign.Center)
                    },
                    onClick = {
                        expanded = false
                        viewModel.tiposTrabajo = opcion
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }

        //-------------------------------------------------

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.precio,
            onValueChange = viewModel::onPrecioChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Money,
                    contentDescription = null,
                    tint = Color(0xFF85A773),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Precio") },
            isError = viewModel.precioError.isNotBlank(),
            trailingIcon = {
                if (viewModel.precioError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.precioError.isNotBlank()) {
            Text(
                text = viewModel.precioError,
                color = MaterialTheme.colorScheme.error
            )
        }

        ExtendedFloatingActionButton(
            modifier = Modifier
                .size(124.dp, 124.dp)
                .wrapContentSize(Alignment.Center),
            text = { Text("Add") },
            contentColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFF3992CC),
            icon = { Icon(imageVector = Icons.TwoTone.Save, contentDescription = "Save") },
            onClick = {
                if (viewModel.HayErroresRegistrando()) {
                    viewModel.nombresError = ""
                    if (viewModel.nombres.isBlank()) {
                        viewModel.nombresError = "  Debe indicar el nombre de la persona"
                    }

                    viewModel.telefonoError = ""
                    if (viewModel.telefono.isBlank()) {
                        viewModel.telefonoError = "  Debe indicar el telefono de la persona"
                    }

                    viewModel.tiposTrabajoError = ""
                    if (viewModel.tiposTrabajo.isBlank()) {
                        viewModel.tiposTrabajoError = "  Debe seleccionar el tipo de trabajo"
                    }

                    viewModel.precioError = ""
                    if (viewModel.precio.isBlank()) {
                        viewModel.precioError = "  Debe indicar el precio del trabajo"
                    }
                } else {
                    viewModel.postPersonas()
                    viewModel.Limpiar()
                }
            }
        )

    }
}