package com.duoc.notariapp.uii

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navegacion() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            Registro(navController = navController)
        }
        composable("paginaPrincipal") {
            PaginaPrincipal(navController = navController)
        }
        composable("perfil") { Perfil(navController = navController) }
        composable("datosPersonales") { DatosPersonales(navController = navController) }

    }
}