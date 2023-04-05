package com.ucne.gestionobrasapp.data.repositoy.tipos

import com.ucne.gestionobrasapp.data.local.dao.TiposDao
import com.ucne.gestionobrasapp.data.local.entity.TiposEntity
import com.ucne.gestionobrasapp.data.local.entity.toTiposDto
import javax.inject.Inject

class TiposRepository @Inject constructor
    (
    private val tiposDao: TiposDao,
    private val tiposApiRepository: TiposApiRepository
) {
    suspend fun insertUpdate(tipos: TiposEntity) {

        tiposDao.insert(tipos)

        tiposApiRepository.putTipos(tipos.tipoTrabajoId!!, tipos.toTiposDto())
    }

    suspend fun delete(tipos: TiposEntity){
        tiposDao.delete(tipos)
        tiposApiRepository.deleteTipos(tipos.tipoTrabajoId!!, tipos.toTiposDto())
    }
}