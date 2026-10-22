package com.duoc.notariapp.uii

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.duoc.notariapp.R


@Composable
fun Registro(navController: NavController) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var errorCorreo by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Notariaexpress", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Realiza tus tramites desde tu casa", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.logonotariaexpress),
            contentDescription = "Logo Notariaexpress",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                errorCorreo = false
            },
            label = { Text("Correo electronico") },
            isError = errorCorreo,
            modifier = Modifier.fillMaxWidth()
        )

        if (errorCorreo) {
            Text(text = "El correo no puede estar vacio", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (correo.isBlank() || contrasena.isBlank()) {
                    errorCorreo = true
                    Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Ingreso correctamente", Toast.LENGTH_SHORT).show()
                    navController.navigate("paginaPrincipal") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesion")
        }
    }
}