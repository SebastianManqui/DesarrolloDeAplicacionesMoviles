package com.duoc.notariapp.remote


import com.duoc.notariapp.model.TramiteApi
import com.duoc.notariapp.model.UsuarioApi
import retrofit2.http.*

interface ApiService {
    @GET("tramite")
    suspend fun obtenerTramites(): List<TramiteApi>

    @POST("tramite")
    suspend fun crearTramite(@Body tramite: TramiteApi): TramiteApi

    @GET("tramite/{tramite_id}")
    suspend fun obtenerTramitePorId(@Path("tramite_id") id: Int): TramiteApi

    @PUT("tramite/{tramite_id}")
    suspend fun actualizarTramite(@Path("tramite_id") id: Int, @Body tramite: TramiteApi): TramiteApi

    @PATCH("tramite/{tramite_id}")
    suspend fun editarTramite(@Path("tramite_id") id: Int, @Body tramite: TramiteApi): TramiteApi

    @DELETE("tramite/{tramite_id}")
    suspend fun eliminarTramite(@Path("tramite_id") id: Int)
}

interface UsuarioApiService {
    @POST("usuario")
    suspend fun crearUsuario(@Body usuario: UsuarioApi): UsuarioApi

    @GET("usuario")
    suspend fun obtenerUsuarios(): List<UsuarioApi>

    @PATCH("usuario/{usuario_id}")
    suspend fun editarUsuario(@Path("usuario_id") id: Int, @Body usuario: UsuarioApi): UsuarioApi

    @GET("usuario/{usuario_id}")
    suspend fun obtenerUsuarioPorId(@Path("usuario_id") id: Int): UsuarioApi

    @DELETE("usuario/{usuario_id}")
    suspend fun eliminarUsuario(@Path("usuario_id") id: Int)

    @PUT("usuario/{usuario_id}")
    suspend fun actualizarUsuario(@Path("usuario_id") id: Int, @Body usuario: UsuarioApi): UsuarioApi
}