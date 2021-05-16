package com.work.kotinproject.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.work.kotinproject.Model.Data
import com.work.kotinproject.Model.User
import com.work.kotinproject.retrofit.UsersService
import dagger.android.support.DaggerAppCompatActivity
import dagger.internal.DaggerCollections
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel() {

    @Inject
    lateinit var usersService:  UsersService

    init {
    }
    private val disposable = CompositeDisposable()
    val users = MutableLiveData<List<User>>()
    val userLoadError=MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchUsers()
    }
    private fun fetchUsers(){
        loading.value = true
        disposable.add(
            usersService.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Data>(){
                    override fun onSuccess(data: Data) {
                        TODO("Not yet implemented")
                        Log.d("error",""+data)
                        users.value = data.users
                        userLoadError.value = false
                        loading.value = false
                    }
                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                        userLoadError.value = true
                        loading.value = false
                        Log.d("error",""+e.printStackTrace())
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}