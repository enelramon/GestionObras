package com.ucne.gestionobrasapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucne.gestionobrasapp.data.remote.dto.NominasDto

@Entity(tableName = "Nominas")
data class NominasEntity(
    @PrimaryKey(autoGenerate = true)
    val nominaId: Int? = null,
    val fecha: String,
    val total: Double,               // Nomina
    val estado: String,
    val proyectoId: Int,
    val personaId: Int,
    val enviado: Boolean = false
)

fun NominasEntity.toNominasDto(): NominasDto {
    return NominasDto(
        nominaId = this.nominaId ?: 0,
        fecha = this.fecha,
        total = this.total,
        estado = this.estado,
        proyectoId = this.proyectoId,
        personaId = this.personaId
    )
}