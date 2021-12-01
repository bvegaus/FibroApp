package com.androidavanzado.fibroapp.paciente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.androidavanzado.fibroapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomePacienteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_paciente)

        val btnLogout = findViewById<Button>(R.id.btnLogOut)
        btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            onBackPressed()
        }
    }

    fun onClick(view: View){
        val id_btn = view.id
        if (id_btn == R.id.btnPersonalInfo){
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}