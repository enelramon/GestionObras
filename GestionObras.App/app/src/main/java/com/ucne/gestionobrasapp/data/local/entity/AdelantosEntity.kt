package com.ucne.gestionobrasapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucne.gestionobrasapp.data.remote.dto.AdelantosDto

@Entity(tableName = "Adelantos")
data class AdelantosEntity(
    @PrimaryKey(autoGenerate = true)
    val adelantoId: Int? = 0,
    val fecha: String,
    val monto: Double,             // Adelanto
    val balance: Double,
    val enviado: Boolean = false
)

fun AdelantosEntity.toAdelantosDto(): AdelantosDto {
    return AdelantosDto(
        adelantoId = this.adelantoId ?: 0,
        fecha = this.fecha,
        monto = this.monto,
        balance = this.balance
    )
}