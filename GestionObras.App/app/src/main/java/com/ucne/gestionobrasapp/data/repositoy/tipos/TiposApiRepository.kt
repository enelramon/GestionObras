package com.ucne.gestionobrasapp.data.repositoy.tipos

import com.ucne.gestionobrasapp.data.remote.dto.*
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface TiposApiRepository
{
    fun getTipos(): Flow<Resource<List<TiposDto>>>
    fun getTiposId(id: Int): Flow<Resource<TiposDto>>
    suspend fun putTipos(id: Int, tiposDto: TiposDto)
    suspend fun deleteTipos(id: Int, tiposDto: TiposDto)
    suspend fun postTipos(tiposDto: TiposDto)
}