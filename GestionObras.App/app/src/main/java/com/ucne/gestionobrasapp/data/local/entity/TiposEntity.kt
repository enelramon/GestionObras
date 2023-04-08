package com.ucne.gestionobrasapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ucne.gestionobrasapp.data.remote.dto.TiposDto

@Entity(tableName = "Tipos")
data class TiposEntity(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "tipoTrabajoId") val tipoTrabajoId: Int?,
    val descripcion: String,           // Tipo
    val precio: Int,
    val proyectoId: Int,
    val enviado: Boolean = false
)

fun TiposEntity.toTiposDto(): TiposDto {
    return TiposDto(
        tipoTrabajoId = this.tipoTrabajoId ?: 0,
        descripcion = this.descripcion,
        precio = this.precio,
        proyectoId = this.proyectoId
    )
}