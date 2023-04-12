package com.ucne.gestionobrasapp.data.remote.dto

data class AdelantosDto(
    val adelantoId: Int,
    val fecha: String,
    val monto: Double,
    val balance: Double,
    val personaId: Int,
    val proyectoId: Int,
)


