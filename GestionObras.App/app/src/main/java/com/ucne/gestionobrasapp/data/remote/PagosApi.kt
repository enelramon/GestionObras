package com.ucne.gestionobrasapp.data.remote

import com.ucne.gestionobrasapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface PagosApi {

    @GET("/api/Pagos")
    suspend fun getPagos(): List<PagosDto>

    @GET("/api/Pagos/{id}")
    suspend fun getPagosId(@Path("id") id: Int): PagosDto

    @POST("/api/Pagos")
    suspend fun postPagos(@Body pagosDto: PagosDto): PagosDto

    @PUT("/api/Pagos/{id}")
    suspend fun putPagos(@Path("id") id: Int, @Body pagosDto: PagosDto): Response<Unit>

    @DELETE("/api/Pagos/{id}")
    suspend fun deletePagos(@Path("id") id: Int): PagosDto
}