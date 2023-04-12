package com.ucne.gestionobrasapp.data.repositoy.personas

import com.ucne.gestionobrasapp.data.local.dao.PersonasDao
import com.ucne.gestionobrasapp.data.local.entity.PersonasEntity
import com.ucne.gestionobrasapp.data.local.entity.toPersonasDto
import javax.inject.Inject

class PersonasRepository @Inject constructor
    (
    private val personasDao: PersonasDao,
    private val nominasApiRepository: PersonasApiRepository
) {
    suspend fun insertUpdate(personas: PersonasEntity) {

        personasDao.insert(personas)

        nominasApiRepository.putPersonas(personas.personaId!!, personas.toPersonasDto())
    }

    suspend fun delete(personas: PersonasEntity){
        personasDao.delete(personas)
        nominasApiRepository.deletePersonas(personas.personaId!!, personas.toPersonasDto())
    }
}