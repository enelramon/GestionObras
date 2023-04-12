package com.ucne.gestionobrasapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.ucne.gestionobrasapp.data.remote.dto.PersonasDto

@Entity(tableName = "Personas")
data class PersonasEntity(
    @PrimaryKey(autoGenerate = true)
    val personaId: Int?= null,
    val nombres: String,            // Persona
    val telefono: String,
    val tipoTrabajoId: Int?,
    val proyectoId: Int?,
    val enviado: Boolean = false
)

fun PersonasEntity.toPersonasDto(): PersonasDto {
    return PersonasDto(
        personaId = this.personaId ?: 0,
        nombres = this.nombres,
        telefono = this.telefono,
        tipoTrabajoId = this.tipoTrabajoId,
        proyectoId = this.proyectoId?:0
    )
}
