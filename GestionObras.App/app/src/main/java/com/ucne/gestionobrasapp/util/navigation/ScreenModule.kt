package com.ucne.gestionobrasapp.util.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenModuleProyectos(val route: String) {
    object Proyectos : ScreenModuleProyectos("proyectos")
    object ProyectoList : ScreenModuleProyectos("proyectos_list")
}

sealed class ScreenModulePersonas(val route: String) {
    object Personas : ScreenModulePersonas("personas")
    object PersonasList : ScreenModulePersonas("personas_list")
}

sealed class ScreenModuleNominas(val route: String) {
    object Nominas : ScreenModuleNominas("nominas")
    object NominasList : ScreenModuleNominas("nominas_list")
}

sealed class ScreenModuleAdelantos(val route: String) {
    object Adelantos : ScreenModuleAdelantos("adelantos")
    object AdelantosList : ScreenModuleAdelantos("adelantos_list")
}

sealed class ScreenModuleTipos(val route: String) {
    object Tipos : ScreenModuleTipos("tipos")
    object TiposList : ScreenModuleTipos("tipos_list")
}

sealed class ScreenModulePagos(val route: String) {
    object Pagos : ScreenModulePagos("pagos")
    object PagosList : ScreenModulePagos("pagos_list")
}


