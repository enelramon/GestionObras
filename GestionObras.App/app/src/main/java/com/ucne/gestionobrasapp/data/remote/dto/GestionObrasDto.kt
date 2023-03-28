package com.ucne.gestionobrasapp.data.remote.dto

data class ProyectoDto(
    val proyectoId: Int? = null,
    val descripcion: String
)

data class PersonasDto(
    val personaId: Int,
    val nombres: String,
    val telefono: String
)

data class TiposDto(
    val tipoId: Int,
    val descripcion: String,
    val precio: Int
)

data class NominasDto(
    val nominaId: Int,
    val fecha: String,
    val total: Double,
    val estado: String
)

data class AdelantosDto(
    val adelantoId: Int,
    val fecha: String,
    val monto: Double,
    val balance: Double
)

data class PagosDto(
    val pagoId: Int,
    val fecha: String,
    val monto: Double,
    val adelanto: Double,
    val total: Double
)
