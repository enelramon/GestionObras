package com.ucne.gestionobrasapp.data.local.dao

import androidx.room.*
import com.ucne.gestionobrasapp.data.local.entity.GestionObrasEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GestionObrasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gestionObrasEntity: GestionObrasEntity)

    @Delete
    suspend fun delete(gestionObrasEntity: GestionObrasEntity)

    @Query(
        """
        SELECT * 
        FROM GestionObras
        WHERE ProyectoId=:proyectoId
        LIMIT 1
    """
    )
    suspend fun find(proyectoId: Int): GestionObrasEntity?

    @Query(
        """SELECT * 
        FROM GestionObras
        ORDER BY proyectoId desc
    """
    )
    fun getList(): Flow<List<GestionObrasEntity>>

    @Query(
        """SELECT * 
        FROM GestionObras
        WHERE enviado=0
    """
    )
    suspend fun getNoEnviadas(): List<GestionObrasEntity>
}