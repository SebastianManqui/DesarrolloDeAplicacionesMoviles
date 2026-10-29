package com.duoc.notariapp.uii

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PaginaPrincipal(navController: NavController) {
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Ya estás aquí */ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("listaSucursales") },
                    icon = { Icon(Icons.Default.Place, contentDescription = "Sucursales") },
                    label = { Text("Sucursales") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("tramites") },
                    icon = { Icon(Icons.Default.List, contentDescription = "Trámites") },
                    label = { Text("Trámites") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("perfil") },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
            Text(text = "Valores estimados de trámites", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))

            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Assignment, contentDescription = null, tint = Color.Gray)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = "Declaración Jurada", fontSize = 15.sp)
                            Text(text = "Firma digital o presencial", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                    Text(text = "$3.500", fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
                }
            }

            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Assignment, contentDescription = null, tint = Color.Gray)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = "Poder Simple", fontSize = 15.sp)
                            Text(text = "Para cobros o trámites básicos", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                    Text(text = "$5.000", fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
                }
            }

            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Assignment, contentDescription = null, tint = Color.Gray)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = "Contrato de Arriendo", fontSize = 15.sp)
                            Text(text = "Formato estándar digital", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                    Text(text = "$12.000", fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "¿Necesitas ayuda? Contáctanos", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("contactoSucursal")
                    },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.SupportAgent,
                        contentDescription = "Soporte",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Centro de Soporte y Ayuda",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Resuelve tus dudas con un asistente en línea",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

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
}
