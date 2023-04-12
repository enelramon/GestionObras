package com.ucne.gestionobrasapp.data.repositoy.nominas

import com.ucne.gestionobrasapp.data.local.dao.NominasDao
import com.ucne.gestionobrasapp.data.local.entity.NominasEntity
import com.ucne.gestionobrasapp.data.local.entity.toNominasDto
import javax.inject.Inject

class NominasRepository @Inject constructor
    (
    private val nominasDao: NominasDao,
    private val nominasApiRepository: NominasApiRepository
) {
    suspend fun insertUpdate(nominas: NominasEntity) {

        nominasDao.insert(nominas)

        nominasApiRepository.putNominas(nominas.nominaId!!, nominas.toNominasDto())
    }

    suspend fun delete(nominas: NominasEntity){
        nominasDao.delete(nominas)
        nominasApiRepository.deleteNominas(nominas.nominaId!!, nominas.toNominasDto())
    }
}