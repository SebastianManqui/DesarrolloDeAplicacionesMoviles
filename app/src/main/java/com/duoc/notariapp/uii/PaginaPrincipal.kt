package com.duoc.notariapp.uii

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PaginaPrincipal(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "NOTARÍA EN LÍNEA", fontSize = 12.sp, color = Color.Gray)
        Text(text = "Página principal", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Bienvenidos a NotariaExpress", fontSize = 28.sp)
        Text(text = "Todo lo que necesitas para avanzar con tranquilidad.", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("estadoTramite")
                },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Tramites en curso", fontSize = 11.sp, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Validacion de firma", fontSize = 18.sp, color = Color.White)
                Text(text = "En revision ", fontSize = 12.sp, color = Color.LightGray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "¿Qué tramite deseas hacer en NotariaExpress?", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Info, contentDescription = null)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "Documentos legales")
                    Text(text = "Guías y archivos personales", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable {
                    navController.navigate("perfil")
                }
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Person, contentDescription = null)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "Perfil del usuario")
                    Text(text = "Datos Personales y tu documentos", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable {
                    navController.navigate("listaSucursales")
                }
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Place, contentDescription = null)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "Ubicaciones de sucursales")
                    Text(text = "Sucursal mas cercana", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable {
                    navController.navigate("tramites")
                }
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.List, contentDescription = null)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "Tramites y seguimiento de tramites realizados")
                    Text(text = "Solicitudes en curso e Historial de tramites Realizados", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = {
                navController.navigate("login") {
                    popUpTo("paginaPrincipal") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesion")
        }
    }
}