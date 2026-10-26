package com.duoc.notariapp.model

data class Tramite(
    val codigo: String,
    val tipo: String,
    val sucursal: String,
    val estado: String,
    val fecha: String,
    val progreso: Float
)