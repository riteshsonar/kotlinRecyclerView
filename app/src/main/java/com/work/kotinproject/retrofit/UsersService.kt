package com.work.kotinproject.retrofit

import com.work.kotinproject.Model.Data
import dagger.android.DaggerApplication
import io.reactivex.Single
import javax.inject.Inject

public class UsersService {
    @Inject
    lateinit var api: UsersApi
    init {
    }
    fun getUsers(): Single<Data>{
        return api.getUsers()
    }
}