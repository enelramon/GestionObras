package com.ucne.gestionobrasapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucne.gestionobrasapp.data.remote.dto.PagosDto

@Entity(tableName = "Pagos")
data class PagosEntity(
    @PrimaryKey(autoGenerate = true)
    val pagoId: Int,
    val fecha: String,
    val monto: Double,                 // Pago
    val adelantoId: Int,
    val total: Double,
    val proyectoId: Int,
    val personaId: Int,
    val enviado: Boolean = false
)

fun PagosEntity.toPagosDto(): PagosDto {
    return PagosDto(
        pagoId = this.pagoId,
        fecha = this.fecha,
        monto = this.monto,
        adelantoId = this.adelantoId,
        total = this.total,
        proyectoId = this.proyectoId,
        personaId = this.personaId
    )
}
