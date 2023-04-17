package com.example.coisos.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coisos.databinding.ActivityLoginBinding
import com.example.coisos.login.viewmodel.LoginViewModel
import com.example.coisos.view.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    companion object {
        fun startLoginActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val buttonRegisterActivity = binding.textViewCreateAccount
        val buttonLogin = binding.buttonLogin

        buttonRegisterActivity.setOnClickListener {
            RegisterActivity.startRegisterActivity(this)
        }

        buttonLogin.setOnClickListener {
            handleLogin()
        }

        observe()
    }

    private fun handleLogin() {
        val identifier = binding.editTextIdentifierLogin.text.toString()
        val password = binding.editTextPasswordLogin.text.toString()

        loginViewModel.doLogin(identifier, password)
    }

    private fun observe() {
        loginViewModel.messageLogin.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        loginViewModel.isLogged.observe(this) {
            if (it) {
                MainActivity.startMainActivity(this)
            }
        }
    }
}