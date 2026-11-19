package com.duoc.notariapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.notariapp.model.TramiteApi
import com.duoc.notariapp.model.UsuarioApi
import com.duoc.notariapp.repository.TramiteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TramitesViewModel : ViewModel() {
    private val repository = TramiteRepository()

    private val _tramites = MutableStateFlow<List<TramiteApi>>(emptyList())
    val tramites: StateFlow<List<TramiteApi>> = _tramites

    private val _usuarios = MutableStateFlow<List<UsuarioApi>>(emptyList())
    val usuarios: StateFlow<List<UsuarioApi>> = _usuarios

    private val _mensajeEstado = MutableStateFlow("")
    val mensajeEstado: StateFlow<String> = _mensajeEstado

    init {
        obtenerTramites()
        obtenerUsuarios()
    }

    fun obtenerTramites() {
        viewModelScope.launch {
            try {
                _tramites.value = repository.obtenerTramites()
            } catch (e: Exception) {
                _mensajeEstado.value = "Error al cargar trámites: ${e.message}"
                e.printStackTrace()
            }
        }
    }

    fun agregarTramite(nombre: String, descripcion: String, precio: Double) {
        viewModelScope.launch {
            _mensajeEstado.value = "Guardando trámite en Xano..."
            try {
                val nuevoTramite = TramiteApi(
                    nombre = nombre.trim(),
                    descripcion = descripcion.trim(),
                    precio = precio
                )
                repository.crearTramite(nuevoTramite)
                _mensajeEstado.value = "Trámite guardado correctamente en Xano"
                obtenerTramites()
            } catch (e: Exception) {
                _mensajeEstado.value = "Error al guardar (POST): ${e.message}"
                e.printStackTrace()
            }
        }
    }

    fun obtenerUsuarios() {
        viewModelScope.launch {
            try {
                _usuarios.value = repository.obtenerUsuarios()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun registrarUsuario(nombre: String, rut: String, correo: String, telefono: String) {
        viewModelScope.launch {
            try {
                val nuevoUsuario = UsuarioApi(
                    nombre = nombre,
                    rut = rut,
                    correo = correo,
                    telefono = telefono
                )
                repository.crearUsuario(nuevoUsuario)
                obtenerUsuarios()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}