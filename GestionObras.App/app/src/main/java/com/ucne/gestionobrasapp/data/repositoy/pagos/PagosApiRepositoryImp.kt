package com.ucne.gestionobrasapp.data.repositoy.pagos

import com.ucne.gestionobrasapp.data.remote.AdelantosApi
import com.ucne.gestionobrasapp.data.remote.PagosApi
import com.ucne.gestionobrasapp.data.remote.dto.AdelantosDto
import com.ucne.gestionobrasapp.data.remote.dto.PagosDto
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PagosApiRepositoryImp @Inject constructor(
    private val gestionObrasApi: PagosApi
): PagosApiRepository {

    override suspend fun putPagos(id: Int, pagosDto: PagosDto) {
        gestionObrasApi.putPagos(id, pagosDto)
    }
    override suspend fun deletePagos(id: Int, pagosDto: PagosDto){
        gestionObrasApi.deletePagos(id, pagosDto)
    }
    override suspend fun postPagos(pagosDto: PagosDto) {
       gestionObrasApi.postPagos(pagosDto)
    }

    override fun getPagos(): Flow<Resource<List<PagosDto>>> = flow{
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val pagos = gestionObrasApi.getPagos() //descarga los pagos de internet, se supone quedemora algo

            emit(Resource.Success(pagos)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override fun getPagosId(id: Int): Flow<Resource<PagosDto>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val pagos = gestionObrasApi.getPagosId(id) //descagar la lista de pagos por el id

            emit(Resource.Success(pagos)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}