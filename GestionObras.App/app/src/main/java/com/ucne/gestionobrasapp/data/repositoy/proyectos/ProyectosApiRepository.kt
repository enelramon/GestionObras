package com.ucne.gestionobrasapp.data.repositoy.proyectos

import com.ucne.gestionobrasapp.data.remote.dto.*
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProyectosApiRepository
{
    fun getProyectos(): Flow<Resource<List<ProyectosDto>>>
    fun getProyectosId(id: Int): Flow<Resource<ProyectosDto>>
    suspend fun putProyectos(id: Int, proyectosDto: ProyectosDto)
    suspend fun deleteProyectos(id: Int)
    suspend fun postProyectos(proyectosDto: ProyectosDto)
}