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

    init {
        obtenerTramites()
        obtenerUsuarios()
    }

    fun obtenerTramites() {
        viewModelScope.launch {
            try {
                _tramites.value = repository.obtenerTramites()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun agregarTramite(nombre: String, descripcion: String, precio: Double) {
        viewModelScope.launch {
            try {
                val nuevoTramite = TramiteApi(
                    nombre = nombre,
                    descripcion = descripcion,
                    precio = precio
                )
                repository.crearTramite(nuevoTramite)
                obtenerTramites() // Vuelve a hacer GET para refrescar la pantalla
            } catch (e: Exception) {
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