package com.ucne.gestionobrasapp.data.repositoy.nominas
import com.ucne.gestionobrasapp.data.remote.NominasApi
import com.ucne.gestionobrasapp.data.remote.dto.NominasDto
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NominasApiRepositoryImp @Inject constructor(
    private val gestionObrasApi: NominasApi
):  NominasApiRepository {

    override suspend fun putNominas(id: Int, nominasDto: NominasDto) {
        gestionObrasApi.putNominas(id, nominasDto)
    }
    override suspend fun deleteNominas(id: Int){
        gestionObrasApi.deleteNominas(id)
    }
    override suspend fun postNominas(nominasDto: NominasDto) {
       gestionObrasApi.postNominas(nominasDto)
    }

    override fun getNominas(): Flow<Resource<List<NominasDto>>> = flow{
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val nominas = gestionObrasApi.getNominas() //descarga los nominas de internet, se supone quedemora algo

            emit(Resource.Success(nominas)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override fun getNominasId(id: Int): Flow<Resource<NominasDto>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val nominas = gestionObrasApi.getNominasId(id) //descagar la lista de nominas por el id

            emit(Resource.Success(nominas)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}