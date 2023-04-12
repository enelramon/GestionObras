package com.ucne.gestionobrasapp.data.repositoy.adelantos

import com.ucne.gestionobrasapp.data.local.dao.AdelantosDao
import com.ucne.gestionobrasapp.data.local.entity.AdelantosEntity
import com.ucne.gestionobrasapp.data.local.entity.toAdelantosDto
import javax.inject.Inject

class AdelantosRepository @Inject constructor
    (
    private val adelantosDao: AdelantosDao,
    private val adelantosApiRepository: AdelantosApiRepository
) {
    suspend fun insertUpdate(adelantos: AdelantosEntity) {

        adelantosDao.insert(adelantos)

        adelantosApiRepository.putAdelantos(adelantos.adelantoId!!, adelantos.toAdelantosDto())
    }

    suspend fun delete(adelantos: AdelantosEntity){
        adelantosDao.delete(adelantos)
        adelantosApiRepository.deleteAdelantos(adelantos.adelantoId!!, adelantos.toAdelantosDto())
    }
}