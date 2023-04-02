package com.ucne.gestionobrasapp.data.local.dao

import androidx.room.*
import com.ucne.gestionobrasapp.data.local.entity.AdelantosEntity
import com.ucne.gestionobrasapp.data.local.entity.PersonasEntity
import com.ucne.gestionobrasapp.data.local.entity.ProyectosEntity
import com.ucne.gestionobrasapp.data.local.entity.TiposEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TiposDao {
    @Upsert
    suspend fun insert(tiposEntity: TiposEntity)

    @Delete
    suspend fun delete(tiposEntity: TiposEntity)

    @Query(
        """
        SELECT *
        FROM Tipos
        WHERE TipoId=:tipoId
        LIMIT 1
    """)
    suspend fun find(tipoId: Int): TiposEntity?

    @Query(
        """SELECT *
        FROM Tipos
        ORDER BY tipoId desc
    """)
    fun getListTipos(): Flow<List<TiposEntity>>

    @Query(
        """SELECT *
        FROM Tipos
        WHERE enviado=0
    """)
    suspend fun getTiposNoEnviados(): List<TiposEntity>
}