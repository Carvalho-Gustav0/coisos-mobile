package com.example.coisos.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coisos.R
import com.example.coisos.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    companion object {
        fun startRegisterActivity(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonRegisterActivity = findViewById<TextView>(R.id.text_view_login_account)

        buttonRegisterActivity.setOnClickListener {
            LoginActivity.startLoginActivity(this)
        }
    }
}