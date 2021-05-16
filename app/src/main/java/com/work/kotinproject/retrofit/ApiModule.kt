package com.work.kotinproject.retrofit

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
@Module
class ApiModule {

    private val BASE_URL="https://reqres.in/api/"
    @Provides
    fun provideUserApi(): UsersApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(UsersApi::class.java)
    }
    fun provideUsersService(): UsersService{
        return UsersService()
    }
}