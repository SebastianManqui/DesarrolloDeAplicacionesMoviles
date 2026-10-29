package com.duoc.notariapp.uii

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SeleccionarTramite(navController: NavController) {
    var tipoTramite by remember { mutableStateOf("") }
    var sucursal by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val tramitesDisponibles = listOf(
        "Declaración Jurada",
        "Poder Simple",
        "Contrato de Arriendo",
        "Validación de Firma",
        "Fotocopia Legalizada"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    if (!navController.popBackStack()) {
                        navController.navigate("paginaPrincipal")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Iniciar Nuevo Trámite", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Ingresa los detalles de la solicitud",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Completa los campos o selecciona uno de la lista rápida.",
            fontSize = 13.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Trámites sugeridos (Toca uno para autocompletar):",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            tramitesDisponibles.forEach { tramite ->
                SuggestionChip(
                    onClick = { tipoTramite = tramite },
                    label = { Text(tramite, fontSize = 12.sp) },
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = tipoTramite,
            onValueChange = { tipoTramite = it },
            label = { Text("Tipo de trámite") },
            placeholder = { Text("Escribe o selecciona un trámite") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = sucursal,
            onValueChange = { sucursal = it },
            label = { Text("Lugar o sucursal donde realizarlo") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                cargando = true
                scope.launch {
                    delay(2000)
                    cargando = false
                    navController.navigate("tramites")
                }
            },
            enabled = !cargando && tipoTramite.isNotBlank() && sucursal.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            if (cargando) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = Color.White
                )
            } else {
                Text("Enviar y agregar solicitud", fontSize = 16.sp)
            }
        }
    }
}
