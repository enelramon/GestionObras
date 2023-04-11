package com.ucne.gestionobrasapp.data.repositoy.proyectos

import com.ucne.gestionobrasapp.data.remote.ProyectosApi
import com.ucne.gestionobrasapp.data.remote.dto.ProyectosDto
import com.ucne.gestionobrasapp.data.repositoy.proyectos.ProyectosApiRepository
import com.ucne.gestionobrasapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProyectosApiRepositoryImp @Inject constructor(
    private val gestionObrasApi: ProyectosApi
): ProyectosApiRepository {

    override suspend fun putProyectos(id: Int, proyectosDto: ProyectosDto) {
        gestionObrasApi.putProyectos(id, proyectosDto)
    }
    override suspend fun deleteProyectos(id: Int){
        gestionObrasApi.deleteProyectos(id)
    }
    override suspend fun postProyectos(proyectosDto: ProyectosDto) {
       gestionObrasApi.postProyectos(proyectosDto)
    }

    override fun getProyectos(): Flow<Resource<List<ProyectosDto>>> = flow{
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val proyectos = gestionObrasApi.getProyectos() //descarga los proyectos de internet, se supone quedemora algo

            emit(Resource.Success(proyectos)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override fun getProyectosId(id: Int): Flow<Resource<ProyectosDto>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val proyectos = gestionObrasApi.getProyectosId(id) //descagar la lista de proyectos por el id

            emit(Resource.Success(proyectos)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}