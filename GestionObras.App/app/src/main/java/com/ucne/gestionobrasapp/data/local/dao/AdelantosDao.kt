package com.ucne.gestionobrasapp.data.local.dao

import androidx.room.*
import com.ucne.gestionobrasapp.data.local.entity.AdelantosEntity
import com.ucne.gestionobrasapp.data.local.entity.PersonasEntity
import com.ucne.gestionobrasapp.data.local.entity.ProyectosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AdelantosDao {
    @Upsert
    suspend fun insert(adelantosEntity: AdelantosEntity)

    @Delete
    suspend fun delete(adelantosEntity: AdelantosEntity)

    @Query(
        """
        SELECT *
        FROM Adelantos
        WHERE AdelantoId=:adelantoId
        LIMIT 1
    """)
    suspend fun find(adelantoId: Int): AdelantosEntity?

    @Query(
        """SELECT *
        FROM Adelantos
        ORDER BY adelantoId desc
    """)
    fun getListAdelantos(): Flow<List<AdelantosEntity>>

    @Query(
        """SELECT *
        FROM Adelantos
        WHERE enviado=0
    """)
    suspend fun getAdelantosNoEnviados(): List<AdelantosEntity>
}