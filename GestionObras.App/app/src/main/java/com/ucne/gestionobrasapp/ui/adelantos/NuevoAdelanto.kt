package com.ucne.gestionobrasapp.ui.adelantos


import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.gestionobrasapp.ui.personas.PersonasApiViewModel
import com.ucne.gestionobrasapp.ui.theme.Shapes
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleAdelantos
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch

import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdelantosScreen(
    adelantoId: Int,
    personaId: Int,
    viewModel: AdelantosApiViewModel = hiltViewModel(),
    navController: NavController,
    onSaveClick: () -> Unit
) {

    remember {
        viewModel.AdelantosbyId(adelantoId)
        0
    }



    var expanded by remember { mutableStateOf(false) }
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
            viewModel.fecha = "$dia/${mes + 1}/$anio"
        }, anio, mes, dia
    )
    /*----------------------------------------Code Start------------------------------------------------------*/

    Column(
        modifier = Modifier
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
                        navController.navigate(ScreenModuleProyectos.DetallesProyecto.route)
                    }
                }
        )

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Adelantos", fontSize = 27.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(10.dp))


        OutlinedTextField(
            modifier = Modifier
                .padding(7.dp)
                .fillMaxWidth(),
            value = viewModel.fecha,
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
            isError = viewModel.fechaError.isNotBlank(),
            trailingIcon = {
                if (viewModel.fechaError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.fechaError.isNotBlank()) {
            Text(
                text = viewModel.fechaError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(7.dp)
                .fillMaxWidth(),
            value = viewModel.personaId,
            enabled = false,
            onValueChange = viewModel::onPersonaIdChanged,
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
            isError = viewModel.personaIdError.isNotBlank(),
            label = { Text("Persona") },
            trailingIcon = {
                if (viewModel.personaIdError.isNotBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.ArrowDropDown, contentDescription = "error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                if (viewModel.personaIdError.isBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.ArrowDropDown, contentDescription = "error",
                        tint = Color(0xFF8A8A8A)
                    )
                }

            }
        )

        if (viewModel.personaIdError.isNotBlank()) {
            Text(
                text = viewModel.personaIdError,
                color = MaterialTheme.colorScheme.error,

                )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(7.dp)
                .fillMaxWidth(),
            value = viewModel.monto,
            onValueChange = viewModel::onMontoChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Paid,
                    contentDescription = null,
                    tint = Color(0xFF85A773),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Monto") },
            isError = viewModel.montoError.isNotBlank(),
            trailingIcon = {
                if (viewModel.montoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.montoError.isNotBlank()) {
            Text(
                text = viewModel.montoError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(7.dp)
                .fillMaxWidth(),
            value = viewModel.balance,
            onValueChange = viewModel::onBalanceChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Savings,
                    contentDescription = null,
                    tint = Color(0xFFFF94A0),
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Balance") },
            isError = viewModel.balanceError.isNotBlank(),
            trailingIcon = {
                if (viewModel.balanceError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.balanceError.isNotBlank()) {
            Text(
                text = viewModel.balanceError,
                color = MaterialTheme.colorScheme.error
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
                    .size(124.dp, 124.dp)
                    .wrapContentSize(Alignment.Center),
                shape = RoundedCornerShape(20.dp),
                text = { Text("Guardar") },
                containerColor = Color(0xFF94B4F5),
                contentColor = Color(0xFFFFFFFF),
                icon = { Icon(imageVector = Icons.TwoTone.Save, contentDescription = "Save") },
                onClick = {

                    if (viewModel.HayErroresRegistrando()) {
                        viewModel.fechaError = ""
                        if (viewModel.fecha.isBlank()) {
                            viewModel.fechaError = "  Debe indicar una fecha"
                        }

                        viewModel.personaIdError = ""
                        if (viewModel.personaId.isBlank()) {
                            viewModel.personaIdError = "  Debe seleccionar una persona"
                        }

                        viewModel.montoError = ""
                        if (viewModel.monto.isBlank()) {
                            viewModel.montoError = "  Debe ingresar un monto de adelanto"
                        }

                        viewModel.balanceError = ""
                        if (viewModel.balance.isBlank()) {
                            viewModel.balanceError = "  Debe ingresar el balance"
                        }
                    } else {
                        viewModel.postAdelantos()
                        viewModel.Limpiar()
                    }
                }
            )
        }
    }
}