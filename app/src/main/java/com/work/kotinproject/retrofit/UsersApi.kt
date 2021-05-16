package com.work.kotinproject.retrofit

import com.work.kotinproject.Model.Data
import io.reactivex.Single
import retrofit2.http.GET

interface UsersApi {
    @GET("users")
    fun getUsers(): Single<Data>
}