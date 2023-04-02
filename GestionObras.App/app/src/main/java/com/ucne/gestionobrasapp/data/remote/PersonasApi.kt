package com.ucne.gestionobrasapp.data.remote

import com.ucne.gestionobrasapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface PersonasApi {

    @GET("/api/Personas")
    suspend fun getPersonas(): List<PersonasDto>

    @GET("/api/Personas/{id}")
    suspend fun getPersonasId(@Path("id") id: Int): PersonasDto

    @POST("/api/Personas")
    suspend fun postPersonas(@Body personasDto: PersonasDto): PersonasDto

    @PUT("/api/Personas/{id}")
    suspend fun putPersonas(@Path("id") id: Int, @Body personasDto: PersonasDto): Response<Unit>

    @DELETE("/api/Personas/{id}")
    suspend fun deletePersonas(@Path("id") id: Int, @Body personasDto: PersonasDto): PersonasDto

}