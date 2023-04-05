package com.ucne.gestionobrasapp.data.remote.dto

data class PersonasDto(
    val personaId: Int,
    val nombres: String,
    val tipoTrabajoId: Int,
    val precio: Double,
    val telefono: String
)
