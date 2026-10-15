package com.duoc.notariapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.duoc.notariapp.ui.theme.Evaluacion2Theme
import com.duoc.notariapp.uii.Registro
import com.duoc.notariapp.uii.CamaraFotos
import com.duoc.notariapp.uii.BotonCargando
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Evaluacion2Theme {
                //Registro()
                //CamaraFotos()
                BotonCargando()
                }
            }
        }
    }

