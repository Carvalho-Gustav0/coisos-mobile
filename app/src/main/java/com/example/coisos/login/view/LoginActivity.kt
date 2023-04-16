package com.example.coisos.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coisos.R
import com.example.coisos.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

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


        val buttonRegisterActivity = findViewById<TextView>(R.id.text_view_create_account)

        buttonRegisterActivity.setOnClickListener {
            RegisterActivity.startRegisterActivity(this)
        }
    }
}