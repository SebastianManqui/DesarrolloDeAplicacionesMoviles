package com.duoc.notariapp.uii


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.duoc.notariapp.viewmodel.TramitesViewModel

@Composable
fun TramitesApiScreen(viewModel: TramitesViewModel = TramitesViewModel()) {
    val tramites by viewModel.tramites.collectAsState()

    var nombreInput by remember { mutableStateOf("") }
    var descripcionInput by remember { mutableStateOf("") }
    var precioInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Solicitar Nuevo Trámite (POST)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nombreInput,
            onValueChange = { nombreInput = it },
            label = { Text("Nombre del trámite") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = descripcionInput,
            onValueChange = { descripcionInput = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = precioInput,
            onValueChange = { precioInput = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val precioDouble = precioInput.toDoubleOrNull() ?: 0.0
                if (nombreInput.isNotBlank() && descripcionInput.isNotBlank()) {
                    viewModel.agregarTramite(nombreInput, descripcionInput, precioDouble)
                    nombreInput = ""
                    descripcionInput = ""
                    precioInput = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Trámite en Xano")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Trámites Disponibles (GET)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        tramites.forEach { tramite ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = tramite.nombre,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = tramite.descripcion,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Valor: $${tramite.precio}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}