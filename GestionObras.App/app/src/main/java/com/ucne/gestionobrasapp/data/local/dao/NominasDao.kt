package com.ucne.gestionobrasapp.data.local.dao

import androidx.room.*
import com.ucne.gestionobrasapp.data.local.entity.AdelantosEntity
import com.ucne.gestionobrasapp.data.local.entity.NominasEntity
import com.ucne.gestionobrasapp.data.local.entity.PersonasEntity
import com.ucne.gestionobrasapp.data.local.entity.ProyectosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NominasDao {
    @Upsert
    suspend fun insert(nominasEntity: NominasEntity)

    @Delete
    suspend fun delete(nominasEntity: NominasEntity)

    @Query(
        """
        SELECT *
        FROM Nominas
        WHERE NominaId=:nominaId
        LIMIT 1
    """)
    suspend fun find(nominaId: Int): NominasEntity?

    @Query(
        """SELECT *
        FROM Nominas
        ORDER BY nominaId desc
    """)
    fun getListNominas(): Flow<List<NominasEntity>>

    @Query(
        """SELECT *
        FROM Nominas
        WHERE enviado=0
    """)
    suspend fun getNominasNoEnviadas(): List<NominasEntity>
}