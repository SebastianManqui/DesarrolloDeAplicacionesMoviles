package com.duoc.notariapp.viewmodel

import androidx.lifecycle.ViewModel
import com.duoc.notariapp.model.Tramite
import com.duoc.notariapp.repository.TramiteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PaginaPrincipalViewModel : ViewModel() {
    private val repository = TramiteRepository()

    private val _tramites = MutableStateFlow<List<Tramite>>(emptyList())
    val tramites: StateFlow<List<Tramite>> = _tramites

    init {
        cargarTramites()
    }
    private fun cargarTramites() {
        _tramites.value = repository.obtenerTramitesEstimados()
    }
}
