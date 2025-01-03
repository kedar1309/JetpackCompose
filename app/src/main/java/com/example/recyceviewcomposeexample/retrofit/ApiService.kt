package com.example.recyceviewcomposeexample.retrofit

import android.telecom.Call
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
   suspend fun getAllPost(): List<Post>
}