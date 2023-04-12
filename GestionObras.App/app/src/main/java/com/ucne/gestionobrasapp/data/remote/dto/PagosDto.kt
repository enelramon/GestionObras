package com.ucne.gestionobrasapp.data.remote.dto

data class PagosDto(
    val pagoId: Int = 0,
    val fecha: String = "",
    val personaId: Int = 0,
    val proyectoId: Int = 0,
    val monto: Double = 0.0,
    val adelantoId: Int = 0,
    val total: Double = 0.0
)
