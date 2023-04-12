package com.ucne.gestionobrasapp.ui.nominas

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.twotone.*
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
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleNominas
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NominaScreen(
    viewModel: NominasApiViewModel = hiltViewModel(),
    navController: NavController
) {
    NominaBody(viewModel, Modifier.fillMaxWidth(), navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NominaBody(
    viewModel: NominasApiViewModel,
    modifier: Modifier,
    navController: NavController
) {
    val scope = rememberCoroutineScope()

    val anio: Int
    val mes: Int
    val dia: Int

    val mCalendar = Calendar.getInstance()
    anio = mCalendar.get(Calendar.YEAR)
    mes = mCalendar.get(Calendar.MONTH)
    dia = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current, { _: DatePicker, anio: Int, mes: Int, dia: Int ->
            viewModel.fechaNomina = "$dia/${mes + 1}/$anio"
        }, anio, mes, dia
    )
    var expended by remember {
        mutableStateOf(false)
    }

    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.CenterEnd)
    ) {

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
                        navController.navigate(ScreenModuleNominas.NominasList.route)
                    }
                }
        )

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Registro de Nomina", fontSize = 27.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .padding(7.dp)
                .fillMaxWidth(),
            value = viewModel.fechaNomina,
            onValueChange = viewModel::onFechaChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                        .wrapContentSize(Alignment.CenterEnd)
                        .clickable {
                            mDatePickerDialog.show()
                        }
                )
            },
            label = { Text("Fecha") },
            isError = viewModel.fechanominaError.isNotBlank(),
            trailingIcon = {
                if (viewModel.fechanominaError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.fechanominaError.isNotBlank()) {
            Text(
                text = viewModel.fechanominaError,
                color = MaterialTheme.colorScheme.error
            )
        }
        //--------------------------------------------

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.totalnomina,
            onValueChange = viewModel::ontotalCanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.MonetizationOn,
                    contentDescription = null,
                    tint = Color(0xFF1F1F1F),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Total") },
            isError = viewModel.totalnominaError.isNotBlank(),
            trailingIcon = {
                if (viewModel.totalnominaError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.totalnominaError.isNotBlank()) {
            Text(
                text = viewModel.totalnominaError,
                color = MaterialTheme.colorScheme.error
            )
        }
        //--------------------------------------------------

        OutlinedTextField(   //Estatus
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { expended = true },
            value = viewModel.estadonomina,
            enabled = false, readOnly = false,
            onValueChange = viewModel::onEstadoCanged,
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
            isError = viewModel.estadonominaError.isNotBlank(),
            label = { Text("Estatus") },
            trailingIcon = {
                if (viewModel.estadonominaError.isNotBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.ArrowDropDown, contentDescription = "error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                if (viewModel.estadonominaError.isBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.ArrowDropDown, contentDescription = "error",
                        tint = Color(0xFF8A8A8A)
                    )
                }

            }
        )
        if (viewModel.estadonominaError.isNotBlank()) {
            Text(
                text = viewModel.estadonominaError,
                color = MaterialTheme.colorScheme.error,
            )
        }

        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)

        ) {
            viewModel.tipoestado.forEach { opcion ->
                DropdownMenuItem(
                    text = {
                        Text(text = opcion, textAlign = TextAlign.Center)
                    },
                    onClick = {
                        expended = false
                        viewModel.estadonomina = opcion
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }

        //-----------------------------------

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.proyectonominaId,
            onValueChange = viewModel::onProyectoIdCanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.AccountTree,
                    contentDescription = null,
                    tint = Color(0xFF1F1F1F),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("ProjectoId") },
            isError = viewModel.proyectonominaIdError.isNotBlank(),
            trailingIcon = {
                if (viewModel.proyectonominaIdError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.proyectonominaIdError.isNotBlank()) {
            Text(
                text = viewModel.proyectonominaIdError,
                color = MaterialTheme.colorScheme.error
            )
        }
        //-----------------------------------------------------
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.personanominaId,
            onValueChange = viewModel::onPersonaIdCanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Person,
                    contentDescription = null,
                    tint = Color(0xFF1F1F1F),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("PersonaId") },
            isError = viewModel.personanominaIdError.isNotBlank(),
            trailingIcon = {
                if (viewModel.personanominaIdError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.personanominaIdError.isNotBlank()) {
            Text(
                text = viewModel.personanominaIdError,
                color = MaterialTheme.colorScheme.error
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .size(124.dp, 124.dp)
                    .wrapContentSize(Alignment.Center),
                shape = RoundedCornerShape(20.dp),
                text = { Text("Guardar") },
                contentColor = Color(0xFFFFFFFF),
                containerColor = Color(0xFF94B4F5),
                icon = { Icon(imageVector = Icons.TwoTone.Save, contentDescription = "Save") },
                onClick = {

                    if (viewModel.HayErroresRegistrando()) {
                        viewModel.fechanominaError = ""
                        if (viewModel.fechaNomina.isBlank()) {
                            viewModel.fechanominaError = "  Debe indicar una fecha"
                        }

                        viewModel.totalnominaError = ""
                        if (viewModel.totalnomina.isBlank()) {
                            viewModel.totalnominaError = "  Debe indicar un total"
                        }

                        viewModel.estadonominaError = ""
                        if (viewModel.estadonomina.isBlank()) {
                            viewModel.estadonominaError = "  Debe indicar un estado"
                        }

                        viewModel.proyectonominaIdError = ""
                        if (viewModel.proyectonominaId.isBlank()) {
                            viewModel.proyectonominaIdError = "  Debe indicar un id del proyecto"
                        }

                        viewModel.personanominaIdError = ""
                        if (viewModel.personanominaId.isBlank()) {
                            viewModel.personanominaIdError = "  Debe indicar un id de la persona"
                        }

                    } else {
                        viewModel.postNominas()
                        navController.navigate(ScreenModuleNominas.NominasList.route)
                    }
                }
            )
        }
    }
    //
}
