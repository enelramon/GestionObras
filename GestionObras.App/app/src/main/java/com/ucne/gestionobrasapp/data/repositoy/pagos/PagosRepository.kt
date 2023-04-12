package com.ucne.gestionobrasapp.data.repositoy.pagos

import com.ucne.gestionobrasapp.data.local.dao.AdelantosDao
import com.ucne.gestionobrasapp.data.local.dao.PagosDao
import com.ucne.gestionobrasapp.data.local.dao.TiposDao
import com.ucne.gestionobrasapp.data.local.entity.AdelantosEntity
import com.ucne.gestionobrasapp.data.local.entity.PagosEntity
import com.ucne.gestionobrasapp.data.local.entity.toAdelantosDto
import com.ucne.gestionobrasapp.data.local.entity.toPagosDto
import com.ucne.gestionobrasapp.data.remote.dto.PagosDto
import com.ucne.gestionobrasapp.data.repositoy.tipos.TiposApiRepository
import javax.inject.Inject

class PagosRepository @Inject constructor
    (
    private val pagosDao: PagosDao,
    private val pagosApiRepository: PagosApiRepository
) {
    suspend fun insertUpdate(pagos: PagosEntity) {

        pagosDao.insert(pagos)

        pagosApiRepository.putPagos(pagos.pagoId, pagos.toPagosDto())
    }

    suspend fun delete(pagos: PagosEntity){
        pagosDao.delete(pagos)
        pagosApiRepository.deletePagos(pagos.pagoId, pagos.toPagosDto())
    }
}