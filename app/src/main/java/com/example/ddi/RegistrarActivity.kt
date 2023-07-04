package com.example.ddi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.CodeatTheme
import com.example.ddi.ui.theme.violetaOscuro

class RegistrarActivity : ComponentActivity() {

    private lateinit var nuevoUsuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    private fun Content() {
        CodeatTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = violetaOscuro
            ) {
                Column(
                    Modifier.fillMaxSize()
                        .padding(25.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    TextCustom(text = "Crear cuenta")
                    Spacer(modifier = Modifier.height(20.dp))
                    val nombre = textFieldCustom(label = "Nombre de usuario", placeholder = "Nombre de usuario")
                    Spacer(modifier = Modifier.height(10.dp))
                    val contrasenia = textFieldPasswordCustom(label = "Contraseña", placeholder = "Contraseña")
                    Spacer(modifier = Modifier.height(10.dp))
                    val confirmar = textFieldPasswordCustom(label = "Confirmar contraseña", placeholder = "Confirmar contraseña")
                    Spacer(modifier = Modifier.height(10.dp))
                    val email = textFieldEmailCustom(label = "Correo electrónico", placeholder = "Correo electrónico")
                    Spacer(modifier = Modifier.height(30.dp))
                    ButtonCustom(text = "Registrar", onClick = {
                        if(validar(nombre, contrasenia, confirmar, email)) {
                            if(contrasenia == confirmar) {
                                nuevoUsuario = Usuario(nombre, contrasenia, email)
                                UsuarioRepositorio.agregar(nuevoUsuario)
                                finish()
                            }
                        }
                    })
                    Spacer(modifier = Modifier.height(20.dp))
                    TextCustom(text = "¿Tienes cuenta?")
                    Spacer(modifier = Modifier.height(10.dp))
                    ButtonCustom(text = "Iniciar Sesión", onClick = { finish() })
                }
            }
        }
    }

    private fun validar(nombre: String, contrasenia: String, confirmar: String, email: String): Boolean {
        return nombre != "" && contrasenia != "" && confirmar != "" && email != "" && email.contains("@")
    }
}