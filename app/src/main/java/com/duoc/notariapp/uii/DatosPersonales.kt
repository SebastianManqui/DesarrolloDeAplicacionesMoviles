package com.duoc.notariapp.uii
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DatosPersonales(navController: NavController) {
    var nombre by remember { mutableStateOf(" Juan Pérez") }
    var rut by remember { mutableStateOf(" 12.345.678-9") }
    var correo by remember { mutableStateOf(" juan.perez@duoc.cl") }
    var telefono by remember { mutableStateOf(" +56912345678") }
    var ciudad by remember { mutableStateOf(" Santiago") }
    var mostrarModal by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
            }
            Column {
                Text(text = "PERFIL DEL USUARIO", fontSize = 10.sp, color = Color.Gray)
                Text(text = "Datos personales", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Tu informacoón", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = rut,
            onValueChange = { rut = it },
            label = { Text("Rut del titular") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electronico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("telefono") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = ciudad,
            onValueChange = { ciudad = it },
            label = { Text("Comuna de recidencia") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = Color(0xFF2E7D32)
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
        if (mensajeError.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = mensajeError, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (nombre.isBlank() || rut.isBlank() || correo.isBlank() || telefono.isBlank()) {
                    mensajeError = "completa todos los campos del formulario"
                } else if (!correo.contains("@")) {
                    mensajeError = " El correo electrónico debe tener un gmail valido"
                } else {
                    mensajeError = ""
                    mostrarModal = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Guardar cambios", fontSize = 16.sp)
        }
    }
    if (mostrarModal) {
        AlertDialog(
            onDismissRequest = { mostrarModal = false },
            title = { Text("datos guardados con exito") },
            text = { Text("Tus datos personales se han actualizado correctamente") },
            confirmButton = {
                Button(
                    onClick = {
                        mostrarModal = false
                        navController.popBackStack()
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}