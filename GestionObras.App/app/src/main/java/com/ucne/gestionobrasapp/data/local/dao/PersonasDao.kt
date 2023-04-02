package com.ucne.gestionobrasapp.data.local.dao

import androidx.room.*
import com.ucne.gestionobrasapp.data.local.entity.PersonasEntity
import com.ucne.gestionobrasapp.data.local.entity.ProyectosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonasDao {
    @Upsert
    suspend fun insert(personasEntity: PersonasEntity)

    @Delete
    suspend fun delete(personasEntity: PersonasEntity)

    @Query(
        """
        SELECT *
        FROM Personas
        WHERE PersonaId=:personaId
        LIMIT 1
    """)
    suspend fun find(personaId: Int): PersonasEntity?

    @Query(
        """SELECT *
        FROM Personas
        ORDER BY personaId desc
    """
    )
    fun getListPersonas(): Flow<List<PersonasEntity>>

    @Query(
        """SELECT *
        FROM Personas
        WHERE enviado=0
    """
    )
    suspend fun getPersonasNoEnviadas(): List<PersonasEntity>
}