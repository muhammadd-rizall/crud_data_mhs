package com.example.crud_mhs_berita.model

data class RegisterRequest(
    val username : String,
    val fullname : String,
    val email : String,
    val password : String,
)
