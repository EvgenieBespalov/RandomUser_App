package com.example.user_list.domain.di

import com.example.user_list.domain.usecase.GetUserListUseCase
import com.example.user_list.presentation.UserListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideUserListModule(): Module =
    module {
        viewModel {
            UserListScreenViewModel(
                getUserListUseCase = get(),
            )
        }
        factory { GetUserListUseCase(repository = get()) }
    }
