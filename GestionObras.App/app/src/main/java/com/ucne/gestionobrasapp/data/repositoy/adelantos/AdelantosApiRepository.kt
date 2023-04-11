package com.ucne.gestionobrasapp.data.repositoy.adelantos

import com.ucne.gestionobrasapp.data.remote.dto.*
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AdelantosApiRepository
{
    fun getAdelantos(): Flow<Resource<List<AdelantosDto>>>
    fun getAdelantosId(id: Int): Flow<Resource<AdelantosDto>>
    suspend fun putAdelantos(id: Int, adelantosDto: AdelantosDto)
    suspend fun deleteAdelantos(id: Int)
    suspend fun postAdelantos(adelantosDto: AdelantosDto)
}