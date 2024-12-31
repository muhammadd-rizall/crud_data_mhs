package com.example.crud_mhs_berita.service

import com.example.crud_mhs_berita.model.LoginResponse
import com.example.crud_mhs_berita.model.RegisterResponse
import com.example.crud_mhs_berita.model.ResponseBerita
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface BeritaService {
    @GET("getBerita.php")
    fun getAllBerita() : Call<ResponseBerita>

    @FormUrlEncoded
    @POST("Register.php")

    fun register(
        @Field("username") username : String,
        @Field("fullname") fullname : String,
        @Field("email") email : String,
        @Field("password") password : String,
    ): Call<RegisterResponse>


    @FormUrlEncoded
    @POST("Login.php")

    fun login(
        @Field("username") username : String,
        @Field("password") password : String,
    ): Call<LoginResponse>
}