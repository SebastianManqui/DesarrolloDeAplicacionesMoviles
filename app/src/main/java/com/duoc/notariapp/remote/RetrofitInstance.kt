package com.duoc.notariapp.remote


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL_TRAMITES = "https://x8ki-letl-twmt.n7.xano.io/api:_37cTRS_/"

    private const val BASE_URL_USUARIOS = "https://x8ki-letl-twmt.n7.xano.io/api:gd65Ee8y/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_TRAMITES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val apiUsuarios: UsuarioApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_USUARIOS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsuarioApiService::class.java)
    }
}