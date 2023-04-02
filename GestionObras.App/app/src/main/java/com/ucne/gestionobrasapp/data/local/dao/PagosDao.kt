package com.ucne.gestionobrasapp.data.local.dao

import androidx.room.*
import com.ucne.gestionobrasapp.data.local.entity.AdelantosEntity
import com.ucne.gestionobrasapp.data.local.entity.PagosEntity
import com.ucne.gestionobrasapp.data.local.entity.PersonasEntity
import com.ucne.gestionobrasapp.data.local.entity.ProyectosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PagosDao {
    @Upsert
    suspend fun insert(pagosEntity: PagosEntity)

    @Delete
    suspend fun delete(pagosEntity: PagosEntity)

    @Query(
        """
        SELECT *
        FROM Pagos
        WHERE PagoId=:pagoId
        LIMIT 1
    """)
    suspend fun find(pagoId: Int): PagosEntity?

    @Query(
        """SELECT *
        FROM Pagos
        ORDER BY pagoId desc
    """)
    fun getListPagos(): Flow<List<PagosEntity>>

    @Query(
        """SELECT *
        FROM Pagos
        WHERE enviado=0
    """)
    suspend fun getPagosNoEnviados(): List<PagosEntity>
}