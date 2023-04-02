package com.ucne.gestionobrasapp.data.repositoy.nominas

import com.ucne.gestionobrasapp.data.remote.dto.*
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface NominasApiRepository
{
    fun getNominas(): Flow<Resource<List<NominasDto>>>
    fun getNominasId(id: Int): Flow<Resource<NominasDto>>
    suspend fun putNominas(id: Int, nominasDto: NominasDto)
    suspend fun deleteNominas(id: Int,  nominasDto: NominasDto)
    suspend fun postNominas( nominasDto: NominasDto)
}