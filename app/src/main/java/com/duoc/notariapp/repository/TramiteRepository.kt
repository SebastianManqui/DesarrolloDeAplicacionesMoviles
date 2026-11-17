package com.duoc.notariapp.repository

import com.duoc.notariapp.model.Tramite
import com.duoc.notariapp.model.TramiteApi
import com.duoc.notariapp.model.UsuarioApi
import com.duoc.notariapp.remote.ApiService
import com.duoc.notariapp.remote.RetrofitInstance
import com.duoc.notariapp.remote.UsuarioApiService

class TramiteRepository(
    private val api: ApiService = RetrofitInstance.api,
    private val apiUsuarios: UsuarioApiService = RetrofitInstance.apiUsuarios
) {
    fun obtenerTramitesEstimados(): List<Tramite> {
        return emptyList()
    }

    suspend fun obtenerTramites(): List<TramiteApi> = api.obtenerTramites()
    suspend fun crearTramite(tramite: TramiteApi): TramiteApi = api.crearTramite(tramite)
    suspend fun obtenerTramitePorId(id: Int): TramiteApi = api.obtenerTramitePorId(id)
    suspend fun actualizarTramite(id: Int, tramite: TramiteApi): TramiteApi = api.actualizarTramite(id, tramite)
    suspend fun editarTramite(id: Int, tramite: TramiteApi): TramiteApi = api.editarTramite(id, tramite)
    suspend fun eliminarTramite(id: Int) = api.eliminarTramite(id)

    suspend fun crearUsuario(usuario: UsuarioApi): UsuarioApi = apiUsuarios.crearUsuario(usuario)
    suspend fun obtenerUsuarios(): List<UsuarioApi> = apiUsuarios.obtenerUsuarios()
    suspend fun editarUsuario(id: Int, usuario: UsuarioApi): UsuarioApi = apiUsuarios.editarUsuario(id, usuario)
    suspend fun obtenerUsuarioPorId(id: Int): UsuarioApi = apiUsuarios.obtenerUsuarioPorId(id)
    suspend fun eliminarUsuario(id: Int) = apiUsuarios.eliminarUsuario(id)
    suspend fun actualizarUsuario(id: Int, usuario: UsuarioApi): UsuarioApi = apiUsuarios.actualizarUsuario(id, usuario)
}