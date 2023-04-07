package com.ucne.gestionobrasapp.ui.pagos


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
import com.ucne.gestionobrasapp.ui.personas.PersonasApiViewModel
import com.ucne.gestionobrasapp.util.navigation.ScreenModulePagos
import com.ucne.gestionobrasapp.util.navigation.ScreenModuleProyectos
import kotlinx.coroutines.launch

import java.util.*
/*
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PagosScreen(
    viewModel: ProyectosApiViewModel = hiltViewModel(),
    navController: NavController
) {

    PagosBody(viewModel, Modifier.fillMaxWidth(), navController)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PagosBody(
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
                        navController.navigate(ScreenModuleProyectos.DetallesProyecto.route)
                    }
                }
        )


            // Aqui va: Fecha, Nombre de la persona en readOnly, Monto, adelanto en readOnly y total en readOnly



        ExtendedFloatingActionButton(
            modifier = Modifier
                .size(124.dp, 124.dp)
                .wrapContentSize(Alignment.Center),
            text = { Text("Pago") },
            contentColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFF3992CC),
            icon = { Icon(imageVector = Icons.TwoTone.Save, contentDescription = "Save") },
            onClick = {

                if (viewModel.HayErroresRegistrando()) {
                    viewModel.montoError = ""
                    if (viewModel.monto.isBlank()) {
                        viewModel.montoError = "  Debe indicar el monto a pagar"
                    }



                } else {
                    viewModel.postPago()
                    //Poner direccion para que lo mande hasta los detalles del proyecto

                }

            }
        )

    }
}*/