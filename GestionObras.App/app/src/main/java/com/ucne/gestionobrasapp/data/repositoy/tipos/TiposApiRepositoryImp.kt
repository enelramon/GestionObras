package com.ucne.gestionobrasapp.data.repositoy.tipos

import com.ucne.gestionobrasapp.data.remote.TiposApi
import com.ucne.gestionobrasapp.data.remote.dto.TiposDto
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TiposApiRepositoryImp @Inject constructor(
    private val gestionObrasApi: TiposApi
): TiposApiRepository {

    override suspend fun putTipos(id: Int, tiposDto: TiposDto) {
        gestionObrasApi.putTipos(id, tiposDto)
    }
    override suspend fun deleteTipos(id: Int){
        gestionObrasApi.deleteTipos(id)
    }
    override suspend fun postTipos(tiposDto: TiposDto) {
       gestionObrasApi.postTipos(tiposDto)
    }

    override fun getTipos(): Flow<Resource<List<TiposDto>>> = flow{
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val tipos = gestionObrasApi.getTipos() //descarga los tipos de internet, se supone quedemora algo

            emit(Resource.Success(tipos)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override fun getTiposId(id: Int): Flow<Resource<TiposDto>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val tipos = gestionObrasApi.getTiposId(id) //descagar la lista de tipos por el id

            emit(Resource.Success(tipos)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}