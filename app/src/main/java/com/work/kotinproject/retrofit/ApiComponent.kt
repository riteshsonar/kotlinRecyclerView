package com.work.kotinproject.retrofit

import androidx.lifecycle.AndroidViewModel
import com.work.kotinproject.ViewModel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: UsersService)
    fun inject(viewModel: ListViewModel)
}