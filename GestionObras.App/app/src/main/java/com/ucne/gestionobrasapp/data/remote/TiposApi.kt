package com.ucne.gestionobrasapp.data.remote

import com.ucne.gestionobrasapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface TiposApi {

    @GET("/api/Tipos")
    suspend fun getTipos(): List<TiposDto>

    @GET("/api/Tipos/{id}")
    suspend fun getTiposId(@Path("id") id: Int): TiposDto

    @POST("/api/Tipos")
    suspend fun postTipos(@Body tiposDto: TiposDto): TiposDto

    @PUT("/api/Tipos/{id}")
    suspend fun putTipos(@Path("id") id: Int, @Body tiposDto: TiposDto): Response<Unit>

    @DELETE("/api/Tipos/{id}")
    suspend fun deleteTipos(@Path("id") id: Int): TiposDto
}