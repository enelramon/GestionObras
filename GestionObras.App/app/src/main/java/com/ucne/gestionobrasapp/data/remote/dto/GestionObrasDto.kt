package com.ucne.gestionobrasapp.data.remote.dto

data class GestionObrasDto(

    val proyectoId: Int? = null,
    val descripcionProyecto: String,

    val personaId: Int,
    val nombresPersona: String,
    val telefonoPersona: String,

    val tipoId: Int,
    val descripcionTipo: String,
    val precioTipo: Int,

    val nominaId: Int,
    val fechaNomina: String,
    val totalNomina: Double,
    val estadoNomina: String,

    val adelantoId: Int,
    val fechaAdelanto: String,
    val montoAdelanto: Double,
    val balanceAdelanto: Double,

    val pagoId: Int,
    val fechaPago: String,
    val montoPago: Double,
    val adelantoPago: Double,
    val totalPago: Double,
)