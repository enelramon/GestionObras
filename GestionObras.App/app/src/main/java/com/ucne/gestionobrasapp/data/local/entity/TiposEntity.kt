package com.ucne.gestionobrasapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucne.gestionobrasapp.data.remote.dto.TiposDto

@Entity(tableName = "Tipos")
data class TiposEntity(
    @PrimaryKey(autoGenerate = true)
    val tipoId: Int? = null,
    val descripcion: String,           // Tipo
    val precio: Int,
    val enviado: Boolean = false
)

fun TiposEntity.toTiposDto(): TiposDto {
    return TiposDto(
        tipoId = this.tipoId ?: 0,
        descripcion = this.descripcion,
        precio = this.precio
    )
}