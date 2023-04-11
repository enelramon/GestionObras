package com.ucne.gestionobrasapp.data.repositoy.adelantos

import com.ucne.gestionobrasapp.data.remote.AdelantosApi
import com.ucne.gestionobrasapp.data.remote.dto.AdelantosDto
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AdelantosApiRepositoryImp @Inject constructor(
    private val gestionObrasApi: AdelantosApi
): AdelantosApiRepository {

    override suspend fun putAdelantos(id: Int, adelantosDto: AdelantosDto) {
        gestionObrasApi.putAdelantos(id, adelantosDto)
    }
    override suspend fun deleteAdelantos(id: Int){
        gestionObrasApi.deleteAdelantos(id)
    }
    override suspend fun postAdelantos(adelantosDto: AdelantosDto) {
       gestionObrasApi.postAdelantos(adelantosDto)
    }

    override fun getAdelantos(): Flow<Resource<List<AdelantosDto>>> = flow{
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val adelantos = gestionObrasApi.getAdelantos() //descarga los adelantos de internet, se supone quedemora algo

            emit(Resource.Success(adelantos)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override fun getAdelantosId(id: Int): Flow<Resource<AdelantosDto>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val adelantos = gestionObrasApi.getAdelantosId(id) //descagar la lista de adelantos por el id

            emit(Resource.Success(adelantos)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}