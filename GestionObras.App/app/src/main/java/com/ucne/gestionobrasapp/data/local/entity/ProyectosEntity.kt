package com.ucne.gestionobrasapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucne.gestionobrasapp.data.remote.dto.*

@Entity(tableName = "Proyectos")
data class ProyectosEntity(
    @PrimaryKey(autoGenerate = true)
    val proyectoId: Int? = null,           // Proyecto
    val descripcion: String,
    val enviado: Boolean = false
)

fun ProyectosEntity.toProyectosDto(): ProyectosDto {
    return ProyectosDto(
        proyectoId = this.proyectoId ?: 0,
        descripcion = this.descripcion
    )
}