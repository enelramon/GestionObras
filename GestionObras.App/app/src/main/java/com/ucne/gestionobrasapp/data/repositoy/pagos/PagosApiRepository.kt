package com.ucne.gestionobrasapp.data.repositoy.pagos

import com.ucne.gestionobrasapp.data.remote.dto.*
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface PagosApiRepository
{
    fun getPagos(): Flow<Resource<List<PagosDto>>>
    fun getPagosId(id: Int): Flow<Resource<PagosDto>>
    suspend fun putPagos(id: Int, pagosDto: PagosDto)
    suspend fun deletePagos(id: Int, pagosDto: PagosDto)
    suspend fun postPagos(pagosDto: PagosDto)
}