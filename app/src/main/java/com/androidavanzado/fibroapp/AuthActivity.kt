package com.androidavanzado.fibroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.androidavanzado.fibroapp.medico.HomeMedicoActivity
import com.androidavanzado.fibroapp.model.emailMedicos
import com.androidavanzado.fibroapp.paciente.HomePacienteActivity
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setup()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_opciones -> {
            Log.i("ActionBar", "Opciones!")
            true
        }
        R.id.action_nuevo -> {
            Log.i("ActionBar", "Nuevo!")
            true
        }
        R.id.action_buscar -> {
            Log.i("ActionBar", "Buscar!")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }





    private fun setup() {
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        /*
        *   LÓGICA DEL BOTÓN SIGN UP (REGISTRARSE)
        * */
        signUpButton.setOnClickListener {
            //Comprobar que el correo y la contraseña no son nulos
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    // Añadimos un listener para que se nos notifique si se ha registrado el usuario o no
                    if (it.isSuccessful) {
                        // borraríamos primero los campos para que al volver a la pantalla no aparezcan
                        emailEditText.setText("")
                        passwordEditText.setText("")
                        showHome(it.result?.user?.email?:"")
                    } else {
                        showAlert()
                    }
                }
            }
        }


        /*
    *   LÓGICA DEL BOTÓN REGISTRO (LOGIN)
    * */
        loginButton.setOnClickListener {
            //Comprobar que el correo y la contraseña no son nulos
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    // Añadimos un listener para que se nos notifique si se ha registrado el usuario o no
                    if (it.isSuccessful) {
                        borrarInfo(emailEditText, passwordEditText)
                        showHome(it.result?.user?.email?:"")
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }


    private fun showAlert() {
        // Vamos a mostrar un Cuadro de diálogo https://developer.android.com/guide/topics/ui/dialogs?hl=es-419
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String) {
        // Aquí habría que especificar a qué home ir según el email
        // Para hacerlo provisional hasta que venga la bbdd, voy a utilizar una constante que
        // me he creado en la clase Constantes.kt
        if (email in emailMedicos) {
            // Home del médico
            val homeIntent = Intent(this, HomeMedicoActivity::class.java).apply {
                putExtra("email", email)
            }
            startActivity(homeIntent)
        } else{
            val homeIntent = Intent(this, HomePacienteActivity::class.java).apply {
                putExtra("email", email)
            }
            startActivity(homeIntent)
        }
    }


    private fun borrarInfo(emailText:EditText, passwdText:EditText){
        emailText.setText("")
        passwdText.setText("")
    }

}