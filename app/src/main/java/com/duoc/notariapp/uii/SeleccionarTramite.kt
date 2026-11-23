package com.duoc.notariapp.uii

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.duoc.notariapp.model.TramiteApi
import com.duoc.notariapp.viewmodel.TramitesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SeleccionarTramite(
    navController: NavController,
    viewModel: TramitesViewModel = viewModel()
) {
    val tramitesApi by viewModel.tramites.collectAsState()
    val mensajeEstado by viewModel.mensajeEstado.collectAsState()

    var tipoTramite by remember { mutableStateOf("") }
    var sucursal by remember { mutableStateOf("") }
    var precioInput by remember { mutableStateOf("") }
    var tramiteSeleccionado by remember { mutableStateOf<TramiteApi?>(null) }
    var cargando by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val tramitesRespaldo = listOf(
        TramiteApi(nombre = "Declaración Jurada", descripcion = "Trámite notarial", precio = 35000.0),
        TramiteApi(nombre = "Poder Simple", descripcion = "Trámite notarial", precio = 45000.0),
        TramiteApi(nombre = "Contrato de Arriendo", descripcion = "Trámite notarial", precio = 35000.0),
        TramiteApi(nombre = "Validación de Firma", descripcion = "Trámite notarial", precio = 15000.0),
        TramiteApi(nombre = "Fotocopia Legalizada", descripcion = "Trámite notarial", precio = 10000.0)
    )

    val listaSugeridos = tramitesApi.ifEmpty { tramitesRespaldo }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
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

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Ingresa los detalles de la solicitud",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Completa los campos o selecciona un trámite disponible en Xano.",
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
            listaSugeridos.forEach { tramite ->
                SuggestionChip(
                    onClick = {
                        tramiteSeleccionado = tramite
                        tipoTramite = tramite.nombre
                        precioInput = if (tramite.precio % 1.0 == 0.0) {
                            tramite.precio.toInt().toString()
                        } else {
                            tramite.precio.toString()
                        }
                    },
                    label = { Text(tramite.nombre, fontSize = 12.sp) },
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = tipoTramite,
            onValueChange = {
                tipoTramite = it
                tramiteSeleccionado = null
            },
            label = { Text("Tipo de trámite") },
            placeholder = { Text("Escribe o selecciona un trámite") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = sucursal,
            onValueChange = { sucursal = it },
            label = { Text("Lugar o sucursal donde realizarlo") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = precioInput,
            onValueChange = { precioInput = it },
            label = { Text("Valor del trámite (ej: 35000)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val precioLimpio = precioInput.replace(".", "").replace("$", "").trim()
                val precioDouble = precioLimpio.toDoubleOrNull() ?: 0.0
                val descripcionConSucursal = "Sucursal: ${sucursal.trim()}"

                cargando = true
                scope.launch {
                    viewModel.agregarTramite(
                        nombre = tipoTramite,
                        descripcion = descripcionConSucursal,
                        precio = precioDouble
                    )
                    delay(1200)
                    cargando = false
                    Toast.makeText(context, "Solicitud enviada y guardada en Xano", Toast.LENGTH_SHORT).show()
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

        if (mensajeEstado.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = mensajeEstado,
                color = if (mensajeEstado.startsWith("Error")) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.primary
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Catálogo en Xano (Toca para seleccionar)",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        tramitesApi.forEach { tramite ->
            val estaSeleccionado = tramiteSeleccionado?.id == tramite.id &&
                    tramiteSeleccionado?.nombre == tramite.nombre

            Card(
                onClick = {
                    tramiteSeleccionado = tramite
                    tipoTramite = tramite.nombre
                    precioInput = if (tramite.precio % 1.0 == 0.0) {
                        tramite.precio.toInt().toString()
                    } else {
                        tramite.precio.toString()
                    }
                },
                border = if (estaSeleccionado) {
                    BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                } else {
                    null
                },
                colors = CardDefaults.cardColors(
                    containerColor = if (estaSeleccionado) {
                        MaterialTheme.colorScheme.secondaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = tramite.nombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = tramite.descripcion,
                        fontSize = 13.sp
                    )
                    Text(
                        text = "Valor: $${tramite.precio}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}