package com.ucne.gestionobrasapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucne.gestionobrasapp.data.remote.dto.GestionObrasDto

@Entity(tableName = "GestionObras")
data class GestionObrasEntity(

    @PrimaryKey(autoGenerate = true)
    val proyectoId: Int? = null,           // Proyecto
    val descripcionProyecto: String,

    val personaId: Int,
    val nombresPersona: String,            // Persona
    val telefonoPersona: String,

    val tipoId: Int,
    val descripcionTipo: String,           // Tipo
    val precioTipo: Int,

    val nominaId: Int,
    val fechaNomina: String,
    val totalNomina: Double,               // Nomina
    val estadoNomina: String,

    val adelantoId: Int,
    val fechaAdelanto: String,
    val montoAdelanto: Double,             // Adelanto
    val balanceAdelanto: Double,

    val pagoId: Int,
    val fechaPago: String,
    val montoPago: Double,                 // Pago
    val adelantoPago: Double,
    val totalPago: Double,


    val enviado: Boolean = false
)

fun GestionObrasEntity.toGestionObrasDto(): GestionObrasDto {
    return GestionObrasDto(

        proyectoId = this.proyectoId ?: 0,
        descripcionProyecto = this.descripcionProyecto,

        personaId = this.personaId,
        nombresPersona = this.nombresPersona,
        telefonoPersona = this.telefonoPersona,

        tipoId = this.tipoId,
        descripcionTipo = this.descripcionTipo,
        precioTipo = this.precioTipo,

        nominaId = this.nominaId,
        fechaNomina = this.fechaNomina,
        totalNomina = this.totalNomina,
        estadoNomina = this.estadoNomina,

        adelantoId = this.adelantoId,
        fechaAdelanto = this.fechaAdelanto,
        montoAdelanto = this.montoAdelanto,
        balanceAdelanto = this.balanceAdelanto,

        pagoId = this.pagoId,
        fechaPago = this.fechaPago,
        montoPago = this.montoPago,
        adelantoPago = this.adelantoPago,
        totalPago = this.totalPago

    )
}
