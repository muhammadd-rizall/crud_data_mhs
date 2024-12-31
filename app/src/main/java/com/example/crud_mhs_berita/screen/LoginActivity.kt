package com.example.crud_mhs_berita.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_mhs_berita.MainActivity
import com.example.crud_mhs_berita.R
import com.example.crud_mhs_berita.model.LoginResponse
import com.example.crud_mhs_berita.model.RegisterResponse
import com.example.crud_mhs_berita.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        //deklarasi widget
        val etUsername : EditText = findViewById(R.id.etUsername)
        val etpassword : EditText = findViewById(R.id.etPassword)
        val btnLogin : Button = findViewById(R.id.btnLogin)
        val txtToRegister : TextView = findViewById(R.id.txtToRegister)

        //ketika dia berhasil login --> pindah ke page berita
        //akan tambah 1 menu untuk tambha data
        //kita akan membuat list data
        //proses delete data


        txtToRegister.setOnClickListener(){
            val toRegister = Intent(this@LoginActivity, RegisterScreenActivity ::class.java)
            startActivity(toRegister)
        }
        btnLogin.setOnClickListener(){
            val username = etUsername.text.toString()
            val password = etpassword.text.toString()

            //validasi input kosong
            if (username.isEmpty() || password.isEmpty()){
                Toast.makeText(this@LoginActivity, "Username atau Password tidak boleh kosong",
                    Toast.LENGTH_SHORT).show()
            }else{
                ApiClient.retrofit.login(username, password).enqueue(
                    object : Callback<LoginResponse>{
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            if (response.isSuccessful){
                                val LoginResponse = response.body()
                                if (LoginResponse != null && response.isSuccessful){
                                    Toast.makeText(this@LoginActivity, response.body()?.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val toMain = Intent(this@LoginActivity, MainActivity ::class.java)
                                    startActivity(toMain)
                                }
                            }else{
                                val errorMessage = response.errorBody()?.string()?: "Unknow Error"
                                Log.e("Login Error", errorMessage)
                                Toast.makeText(this@LoginActivity, "Login Failur",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(this@LoginActivity, t.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}