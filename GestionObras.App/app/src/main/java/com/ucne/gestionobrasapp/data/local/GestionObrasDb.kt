package com.ucne.gestionobrasapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.gestionobrasapp.data.local.dao.GestionObrasDao
import com.ucne.gestionobrasapp.data.local.entity.GestionObrasEntity

@Database(
    entities = [
        GestionObrasEntity::class
    ],
    version = 1
)
abstract class GestionObrasDb: RoomDatabase() {
    abstract val gestionObrasDao: GestionObrasDao
}
