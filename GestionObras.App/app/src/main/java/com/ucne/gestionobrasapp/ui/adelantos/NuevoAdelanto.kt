package com.ucne.gestionobrasapp.ui.adelantos


import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.ucne.gestionobrasapp.ui.theme.Shapes
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleAdelantos
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch

import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdelantosScreen(
    viewModel: AdelantosApiViewModel = hiltViewModel(),
    navController: NavController
) {

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
            tint = Color(0xCD8595FF),
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
                .fillMaxWidth()
                .padding(4.dp)
                .clip(Shapes.large)
                .wrapContentSize(Alignment.Center),
            value = viewModel.fecha,
            onValueChange = viewModel::onFechaChanged,
            label = { Text(text = "Fecha") },
            isError = viewModel.fechaError.isNotBlank(),
            readOnly = false,
            trailingIcon = {
                if (viewModel.fechaError.isNotBlank()) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "error",
                        modifier = Modifier
                            .size(33.dp)
                            .padding(4.dp)
                            .wrapContentSize(Alignment.CenterEnd)
                            .clickable {
                                mDatePickerDialog.show()
                            }
                    )
                }else{
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
                }
            }
        )
        if (viewModel.fechaError.isNotBlank()) {
            Text(
                text = viewModel.fechaError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = viewModel.fechaError,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            value = viewModel.personaId,
            onValueChange = viewModel::onPersonaIdChanged,
            singleLine = true,
            label = { Text("Persona") },
            isError = viewModel.personaIdError.isNotBlank(),
            trailingIcon = {
                if (viewModel.personaIdError.isNotBlank()) {
                    Icon(
                        imageVector = Icons.TwoTone.Person,
                        contentDescription = "error"
                    )
                }else{
                    Icon(
                        imageVector = Icons.TwoTone.Person,
                        contentDescription = null
                    )
                }
            }
        )
        if (viewModel.personaIdError.isNotBlank()) {
            Text(
                text = viewModel.personaIdError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = viewModel.personaIdError,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            value = viewModel.monto,
            onValueChange = viewModel::onMontoChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Money,
                    contentDescription = null,
                    tint = Color(0xFF94B4F5),
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
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = viewModel.montoError,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            value = viewModel.balance,
            onValueChange = viewModel::onBalanceChanged,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Money,
                    contentDescription = null,
                    tint = Color(0xFF94B4F5),
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
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = viewModel.balanceError,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.medium)
                .wrapContentSize(Alignment.BottomCenter)
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .size(120.dp, 120.dp)
                    .clip(Shapes.medium)
                    .wrapContentSize(Alignment.Center),
                text = { Text("Guardar") },
                containerColor = Color(0xFF1E88E5),
                contentColor = Color(0xFFFFFFFF),
                icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
                onClick = {
                    viewModel.postAdelantos()
                }
            )
        }
    }
}