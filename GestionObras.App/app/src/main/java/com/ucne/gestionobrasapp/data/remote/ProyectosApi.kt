package com.ucne.gestionobrasapp.data.remote

import com.ucne.gestionobrasapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface ProyectosApi {

    @GET("/api/Proyectos")
    suspend fun getProyectos(): List<ProyectosDto>

    @GET("/api/Proyectos/{id}")
    suspend fun getProyectosId(@Path("id") id: Int): ProyectosDto

    @POST("/api/Proyectos")
    suspend fun postProyectos(@Body proyectosDto: ProyectosDto): ProyectosDto

    @PUT("/api/Proyectos/{id}")
    suspend fun putProyectos(@Path("id") id: Int, @Body proyectosDto: ProyectosDto): Response<Unit>

    @DELETE("/api/Proyectos/{id}")
    suspend fun deleteProyectos(@Path("id") id: Int, @Body proyectosDto: ProyectosDto): ProyectosDto

}