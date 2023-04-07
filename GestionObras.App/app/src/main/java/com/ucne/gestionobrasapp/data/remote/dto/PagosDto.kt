package com.ucne.gestionobrasapp.data.remote.dto

data class PagosDto(
    val pagoId: Int,
    val fecha: String,
    val monto: Double,
    val adelanto: Double,
    val total: Double,
    val proyectoId: Int,
)
