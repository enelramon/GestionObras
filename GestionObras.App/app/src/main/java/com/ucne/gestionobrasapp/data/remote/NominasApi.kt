package com.ucne.gestionobrasapp.data.remote

import com.ucne.gestionobrasapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface NominasApi {

    @GET("/api/Nominas")
    suspend fun getNominas(): List<NominasDto>

    @GET("/api/Nominas/{id}")
    suspend fun getNominasId(@Path("id") id: Int): NominasDto

    @POST("/api/Nominas")
    suspend fun postNominas(@Body nominasDto: NominasDto): NominasDto

    @PUT("/api/Nominas/{id}")
    suspend fun putNominas(@Path("id") id: Int, @Body nominasDto: NominasDto): Response<Unit>

    @DELETE("/api/Nominas/{id}")
    suspend fun deleteNominas(@Path("id") id: Int, @Body nominasDto: NominasDto): NominasDto

}