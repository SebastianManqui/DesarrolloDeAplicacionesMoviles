package com.duoc.notariapp.uii

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
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
fun EstadoTramite(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
                Column {
                    Text(text = "TRAMITES Y SEGUIMIENTO", fontSize = 10.sp, color = Color.Gray)
                    Text(text = "Estado del trámite", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
            IconButton(onClick = { /* Notificaciones */ }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notificaciones",
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            color = Color(0xFFFBE8EC),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "En revisión",
                fontSize = 11.sp,
                color = Color(0xFF8B263E),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Autenticacion de firma", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "NT-2026-0842 · Notaría 1 - Alameda 1566",
            fontSize = 13.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Avance de tu solicitud", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(36.dp)
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = "Solicitud recibida", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(text = "12 oct · 10:24 a. m.", fontSize = 12.sp, color = Color.Gray)
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "2", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(36.dp)
                        .background(Color.LightGray)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = "Revisión de documentos", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(text = "En curso · 13 oct, 9:15 a. m.", fontSize = 12.sp, color = Color.Gray)
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(Color(0xFFE0E0E0), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "3", color = Color.Gray, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(36.dp)
                        .background(Color.LightGray)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = "Firma en la sucursal", fontWeight = FontWeight.Medium, fontSize = 14.sp, color = Color.Gray)
                Text(text = "Te avisaremos para programar tu visita.", fontSize = 12.sp, color = Color.Gray)
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(Color(0xFFE0E0E0), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "4", color = Color.Gray, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = "Documento disponible", fontWeight = FontWeight.Medium, fontSize = 14.sp, color = Color.Gray)
                Text(text = "disponible en  Mis documentos.", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFAF0F3))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Observacion de la notaria",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF8B263E)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Recibimos tus documentos y estamos verificando la informacion",
                    fontSize = 13.sp,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Actualizado el 13 oct 2026 · 9:15 a. m.",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = { /* Contactar sucursal */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Contactar a la sucursal")
        }
    }
}