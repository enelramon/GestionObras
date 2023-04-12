package com.ucne.gestionobrasapp.data.remote

import com.ucne.gestionobrasapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface AdelantosApi {

    @GET("/api/Adelantos")
    suspend fun getAdelantos(): List<AdelantosDto>

    @GET("/api/Adelantos/{id}")
    suspend fun getAdelantosId(@Path("id") id: Int): AdelantosDto

    @POST("/api/Adelantos")
    suspend fun postAdelantos(@Body adelantosDto: AdelantosDto): AdelantosDto

    @PUT("/api/Adelantos/{id}")
    suspend fun putAdelantos(@Path("id") id: Int, @Body adelantosDto: AdelantosDto): Response<Unit>

    @DELETE("/api/Adelantos/{id}")
    suspend fun deleteAdelantos(@Path("id") id: Int, @Body adelantosDto: AdelantosDto): AdelantosDto

}