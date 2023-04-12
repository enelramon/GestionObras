package com.ucne.gestionobrasapp.data.repositoy.personas

import com.ucne.gestionobrasapp.data.remote.PersonasApi
import com.ucne.gestionobrasapp.data.remote.dto.PersonasDto
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PersonasApiRepositoryImp @Inject constructor(
    private val gestionObrasApi: PersonasApi
): PersonasApiRepository {

    override suspend fun putPersonas(id: Int, personasDto: PersonasDto) {
        gestionObrasApi.putPersonas(id, personasDto)
    }
    override suspend fun deletePersonas(id: Int, personasDto: PersonasDto){
        gestionObrasApi.deletePersonas(id, personasDto)
    }
    override suspend fun postPersonas(personasDto: PersonasDto) {
       gestionObrasApi.postPersonas(personasDto)
    }

    override fun getPersonas(): Flow<Resource<List<PersonasDto>>> = flow{
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val personas = gestionObrasApi.getPersonas() //descarga los personas de internet, se supone quedemora algo

            emit(Resource.Success(personas)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override fun getPersonasId(id: Int): Flow<Resource<PersonasDto>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val personas = gestionObrasApi.getPersonasId(id) //descagar la lista de personas por el id

            emit(Resource.Success(personas)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}