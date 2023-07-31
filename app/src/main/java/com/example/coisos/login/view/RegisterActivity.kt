package com.example.coisos.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coisos.databinding.ActivityRegisterBinding
import com.example.coisos.login.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    companion object {
        fun startRegisterActivity(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        val buttonRegisterActivity = binding.textViewLoginAccount
        val buttonRegister = binding.buttonRegister

        buttonRegisterActivity.setOnClickListener {
            LoginActivity.startLoginActivity(this)
        }

        buttonRegister.setOnClickListener {
            handleLogin()
        }

        observe()
    }

    private fun handleLogin() {
        val name = binding.editTextUsernameRegister.text.toString()
        val cpf = binding.editTextCpf.text.toString()
        val email = binding.editTextEmailRegister.text.toString()
        val password = binding.editTextPasswordRegister.text.toString()

        registerViewModel.doRegister(name, cpf, email, password)
    }

    private fun observe() {
        registerViewModel.messageRegister.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        registerViewModel.isRegistered.observe(this) {
            if (it) {
                LoginActivity.startLoginActivity(this)
            }
        }
    }
}