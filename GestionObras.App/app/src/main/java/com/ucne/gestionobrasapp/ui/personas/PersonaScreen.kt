package com.ucne.gestionobrasapp.ui.personas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    navController: NavController,

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
                        navController.navigate(ScreenModuleProyectos.DetallesProyecto.route)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.tipoTrabajoId,
            onValueChange = viewModel::onTipoChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Construction,
                    contentDescription = null,
                    tint = Color(0xFFFFF072),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("TipoTrabajoId") },
            isError = viewModel.tipoIdError.isNotBlank(),
            trailingIcon = {
                if (viewModel.tipoIdError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.tipoIdError.isNotBlank()) {
            Text(
                text = viewModel.tipoIdError,
                color = MaterialTheme.colorScheme.error
            )
        }

        //-------------------------------------------------

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.projectoId,
            onValueChange = viewModel::onProjecChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Engineering,
                    contentDescription = null,
                    tint = Color(0xFFF74747),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("ProjectoId") },
            isError = viewModel.projectoIdError.isNotBlank(),
            trailingIcon = {
                if (viewModel.projectoIdError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.projectoIdError.isNotBlank()) {
            Text(
                text = viewModel.projectoIdError,
                color = MaterialTheme.colorScheme.error
            )
        }

        //------------------------------------------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            ExtendedFloatingActionButton(
                modifier = Modifier
                    .size(124.dp, 124.dp)
                    .wrapContentSize(Alignment.Center),
                shape = RoundedCornerShape(20.dp),
                text = { Text("Añadir") },
                contentColor = Color(0xFFFFFFFF),
                containerColor = Color(0xFF94B4F5),
                icon = { Icon(imageVector = Icons.TwoTone.PersonAdd, contentDescription = "Save") },
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

                        viewModel.tipoIdError = ""
                        if (viewModel.tipoTrabajoId.isBlank()) {
                            viewModel.tipoIdError = "  Debe indicar el Id Tipo de trabajo"
                        }

                        viewModel.projectoIdError = ""
                        if (viewModel.projectoId.isBlank()) {
                            viewModel.projectoIdError = "  Debe indicar el Id del Projecto"
                        }

                    } else {
                        viewModel.postPersonas()
                        viewModel.Limpiar()
                        navController.navigate(ScreenModuleProyectos.DetallesProyecto.route)
                    }
                }
            )
        }
    }
}