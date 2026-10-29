package com.duoc.notariapp.repository


import com.duoc.notariapp.model.Tramite

class TramiteRepository {
    fun obtenerTramitesEstimados(): List<Tramite> {
        return listOf(
            Tramite(1, "Declaración Jurada", "Firma digital o presencial", 3500),
            Tramite(2, "Poder Simple", "Para cobros o trámites básicos", 5000),
            Tramite(3, "Contrato de Arriendo", "Formato estándar digital", 12000)
        )
    }
}
