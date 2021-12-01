package com.androidavanzado.fibroapp.paciente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.androidavanzado.fibroapp.R

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        //Log.e("ERROR", "ESTO ES UN ERROR DE EJEMPLO")
        val buttonActualizar = findViewById<Button>(R.id.buttonRegistro)
        val telefono = findViewById<EditText>(R.id.editTextPhone)
        buttonActualizar.setOnClickListener {

            if(telefono.text.toString().length != 9){
                Log.e("ERROR", "ESTO ES UN ERROR DE EJEMPLO")
                telefono?.error = "Teléfono incorrecto"
            }

        }
    }
}