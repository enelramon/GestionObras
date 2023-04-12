package com.ucne.gestionobrasapp.util.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenModuleProyectos(val route: String) { // Proyecto
    object Splash : ScreenModuleProyectos("navigation")
    object Proyectos : ScreenModuleProyectos("proyectos")
    object ProyectoList : ScreenModuleProyectos("proyectos_list")
    object DetallesProyecto : ScreenModuleProyectos("detalles_proyecto")
}

sealed class ScreenModulePersonas(val route: String) { // Personas (Trabajadores)
    object Personas : ScreenModulePersonas("personas")
    object PersonasList : ScreenModulePersonas("personas_list")
}

sealed class ScreenModuleAdelantos(val route: String) { // Adelantos
    object Adelantos : ScreenModuleAdelantos("adelantos")
    object AdelantosList : ScreenModuleAdelantos("adelantos_list")
}

sealed class ScreenModulePagos(val route: String) { // Pagos
    object Pagos : ScreenModulePagos("pagos")
    object PagosList : ScreenModulePagos("pagos_list")
}

sealed class ScreenModuleNominas(val route: String) { // Nominas
    object Nominas : ScreenModuleNominas("nominas")
    object NominasList : ScreenModuleNominas("nominas_list")
}

sealed class ScreenModuleAcercade(val route: String) { // Acerca de

    object InfoInicio : ScreenModuleAcercade("info_inicio")

    object InfoDetallesProyecto : ScreenModuleAcercade("info_detalles_proyecto")
}


