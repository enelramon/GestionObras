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

@Entity(tableName = "Personas")
data class PersonasEntity(
    @PrimaryKey(autoGenerate = true)
    val personaId: Int?= null,
    val nombres: String,            // Persona
    val telefono: String,
    val enviado: Boolean = false
)

@Entity(tableName = "Tipos")
data class TiposEntity(
    @PrimaryKey(autoGenerate = true)
    val tipoId: Int? = null,
    val descripcion: String,           // Tipo
    val precio: Int,
    val enviado: Boolean = false
)


@Entity(tableName = "Nominas")
data class NominasEntity(
    @PrimaryKey(autoGenerate = true)
    val nominaId: Int? = null,
    val fecha: String,
    val total: Double,               // Nomina
    val estado: String,
    val enviado: Boolean = false
)

@Entity(tableName = "Adelatos")
data class AdelantosEntity(
    @PrimaryKey(autoGenerate = true)
    val adelantoId: Int? = 0,
    val fecha: String,
    val monto: Double,             // Adelanto
    val balance: Double,
    val enviado: Boolean = false
)

@Entity(tableName = "Pagos")
data class PagosEntity(
    @PrimaryKey(autoGenerate = true)
    val pagoId: Int,
    val fecha: String,
    val monto: Double,                 // Pago
    val adelanto: Double,
    val total: Double,
    val enviado: Boolean = false
)



fun ProyectosEntity.toGestionObrasDto(): ProyectoDto {
    return ProyectoDto(
        proyectoId = this.proyectoId ?: 0,
        descripcion = this.descripcion,
    )
}

fun PersonasEntity.toGestionObrasDto(): PersonasDto {
    return PersonasDto(
        personaId = this.personaId ?: 0,
        nombres = this.nombres,
        telefono = this.telefono,
    )
}

fun TiposEntity.toGestionObrasDto(): TiposDto {
    return TiposDto(
        tipoId = this.tipoId ?: 0,
        descripcion = this.descripcion,
        precio = this.precio
    )
}

fun NominasEntity.toGestionObrasDto(): NominasDto {
    return NominasDto(
        nominaId = this.nominaId ?: 0,
        fecha = this.fecha,
        total = this.total,
        estado = this.estado,
    )
}

fun AdelantosEntity.toGestionObrasDto(): AdelantosDto {
    return AdelantosDto(
        adelantoId = this.adelantoId ?: 0,
        fecha = this.fecha,
        monto = this.monto,
        balance = this.balance
    )
}

fun PagosEntity.toGestionObrasDto(): PagosDto {
    return PagosDto(
        pagoId = this.pagoId ?: 0,
        fecha = this.fecha,
        monto = this.monto,
        adelanto = this.adelanto,
        total = this.total
    )
}
