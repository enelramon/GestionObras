package com.ucne.gestionobrasapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.gestionobrasapp.data.local.dao.*
import com.ucne.gestionobrasapp.data.local.entity.*
import com.ucne.gestionobrasapp.data.remote.dto.AdelantosDto
import com.ucne.gestionobrasapp.data.remote.dto.NominasDto
import com.ucne.gestionobrasapp.data.remote.dto.TiposDto

@Database(
    entities = [
        ProyectosEntity::class,
        PersonasEntity::class,
        PagosEntity::class,
        AdelantosEntity::class,
        NominasEntity::class,
        TiposEntity::class
    ],
    version = 1
)
abstract class RoomGestionObrasDb : RoomDatabase() {
    abstract val proyectosDao: ProyectosDao
    abstract val personasDao: PersonasDao
    abstract val adelantosDao: AdelantosDao
    abstract val nominasDao: NominasDao
    abstract val tiposDao: TiposDao
    abstract val pagosDao: PagosDao
}