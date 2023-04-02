package com.ucne.gestionobrasapp.data.repositoy.personas

import com.ucne.gestionobrasapp.data.remote.dto.*
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface PersonasApiRepository
{
    fun getPersonas(): Flow<Resource<List<PersonasDto>>>
    fun getPersonasId(id: Int): Flow<Resource<PersonasDto>>
    suspend fun putPersonas(id: Int, personasDto: PersonasDto)
    suspend fun deletePersonas(id: Int, personasDto: PersonasDto)
    suspend fun postPersonas(personasDto: PersonasDto)
}