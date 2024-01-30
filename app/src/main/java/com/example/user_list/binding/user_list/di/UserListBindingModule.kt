package com.example.user_list.binding.user_list.di

import com.example.user_list.binding.user_list.converter.UserListConverter
import com.example.user_list.binding.user_list.repository.UserAdapterUserListModule
import com.example.user_list.domain.repositories.UserListRepository
import org.koin.core.module.Module
import org.koin.dsl.module
import repository.ApiRepository

private fun provideUserAdapterRepository(
    apiRepository: ApiRepository,
    converter: UserListConverter
): UserListRepository = UserAdapterUserListModule(converter, apiRepository)

fun provideUserListBindingModule(): Module =
    module {
        factory { UserListConverter() }

        single {
            provideUserAdapterRepository(
                apiRepository = get(),
                converter = get()
            )
        }
    }