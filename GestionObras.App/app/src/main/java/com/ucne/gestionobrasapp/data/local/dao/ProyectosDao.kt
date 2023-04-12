package com.ucne.gestionobrasapp.data.local.dao

import androidx.room.*
import com.ucne.gestionobrasapp.data.local.entity.ProyectosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProyectosDao {
    @Upsert
    suspend fun insert(proyectosEntity: ProyectosEntity)

    @Delete
    suspend fun delete(proyectosEntity: ProyectosEntity)

    @Query(
        """
        SELECT *
        FROM Proyectos
        WHERE ProyectoId=:proyectoId
        LIMIT 1
    """)
    suspend fun find(proyectoId: Int): ProyectosEntity?

    @Query(
        """SELECT *
        FROM Proyectos
        ORDER BY proyectoId desc
    """)
    fun getListProyectos(): Flow<List<ProyectosEntity>>

    @Query(
        """SELECT *
        FROM Proyectos
        WHERE enviado=0
    """)
    suspend fun getProyectosNoEnviados(): List<ProyectosEntity>
}