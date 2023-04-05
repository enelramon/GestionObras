package com.ucne.gestionobrasapp.ui.personas

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.twotone.ArrowCircleLeft
import androidx.compose.material.icons.twotone.ArrowDropDown
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material.icons.twotone.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonasScreen(
    personaId: Int,
    navController: NavController,
    viewModel: PersonasApiViewModel = hiltViewModel(),
    onSaveClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    remember {
        viewModel.PersonasbyId(personaId)
        0
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.CenterEnd)
    ) {

        Spacer(Modifier.height(20.dp))

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Registro de Personal", fontSize = 27.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .wrapContentWidth(Alignment.CenterHorizontally),
            value = viewModel.nombres,
            label = { Text(text = "Nombre") },
            onValueChange = viewModel::onNombresChanged,
            isError = viewModel.nombresError.isNotBlank(),
            trailingIcon = {
                if (viewModel.nombresError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                } else {
                    Icon(imageVector = Icons.TwoTone.Check, contentDescription = "success")
                }
            }
        )
        if (viewModel.nombresError.isNotBlank()) {
            Text(
                text = viewModel.nombresError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = viewModel.nombresError,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .wrapContentSize(Alignment.Center),
            value = viewModel.telefono,
            label = { Text(text = "Telefono") },
            onValueChange = viewModel::onTelefonoChanged,
            isError = viewModel.telefonoError.isNotBlank(),
            trailingIcon = {
                if (viewModel.telefonoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                } else {
                    Icon(imageVector = Icons.TwoTone.Check, contentDescription = "success")
                }
            }
        )
        if (viewModel.telefonoError.isNotBlank()) {
            Text(
                text = viewModel.telefonoError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = viewModel.telefonoError,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .clickable { expanded = true }
                .padding(4.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            readOnly = true, enabled = false,
            value = viewModel.tiposTrabajo,
            label = { Text(text = "Tipo de trabajo") },
            onValueChange = { viewModel.tiposTrabajo = it },
            trailingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.ArrowDropDown,
                    contentDescription = null
                )
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .padding(5.dp)
                    .size(123.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                viewModel.tiposTrabajo.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(text = item.toString(), textAlign = TextAlign.Center)
                        },
                        onClick = {
                            expanded = false
                            viewModel.tiposTrabajo = item.toString()
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .wrapContentSize(Alignment.Center),
            value = viewModel.precio,
            label = { Text(text = "Precio") },
            onValueChange = viewModel::onPrecioChanged,
            isError = viewModel.precioError.isNotBlank(),
            trailingIcon = {
                if (viewModel.precioError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                } else {
                    Icon(imageVector = Icons.TwoTone.Check, contentDescription = "success")
                }
            }
        )
        if (viewModel.precioError.isNotBlank()) {
            Text(
                text = viewModel.precioError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = viewModel.precioError,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .size(60.dp, 50.dp)
                    .wrapContentSize(Alignment.Center),
                text = {},
                containerColor = Color.Blue,
                contentColor = Color.Blue,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = "Save",
                        tint = Color.White
                    )
                },
                onClick = {
                    viewModel.putPersonas(personaId)
                    onSaveClick()
                }
            )
        }
    }
}

