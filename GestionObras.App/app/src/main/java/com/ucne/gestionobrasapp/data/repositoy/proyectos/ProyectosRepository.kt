package com.ucne.gestionobrasapp.data.repositoy.proyectos

import com.ucne.gestionobrasapp.data.local.dao.ProyectosDao
import com.ucne.gestionobrasapp.data.local.entity.ProyectosEntity
import com.ucne.gestionobrasapp.data.local.entity.toProyectosDto
import com.ucne.gestionobrasapp.data.repositoy.proyectos.ProyectosApiRepository
import javax.inject.Inject

class ProyectosRepository @Inject constructor
    (
    private val proyectosDao: ProyectosDao,
    private val proyectosApiRepository: ProyectosApiRepository
) {
    suspend fun insertUpdate(proyectos: ProyectosEntity) {

        proyectosDao.insert(proyectos)

        proyectosApiRepository.putProyectos(proyectos.proyectoId!!, proyectos.toProyectosDto())
    }

    suspend fun delete(proyectos: ProyectosEntity){
        proyectosDao.delete(proyectos)
        proyectosApiRepository.deleteProyectos(proyectos.proyectoId!!, proyectos.toProyectosDto())
    }
}